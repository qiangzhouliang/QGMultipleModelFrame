package com.qzl.lzshzz.ucenter.auth.service;

import com.alibaba.fastjson.JSON;
import com.qzl.lzshzz.common.SmsSendInfo;
import com.qzl.lzshzz.common.client.HttpSend;
import com.qzl.lzshzz.common.client.LogType;
import com.qzl.lzshzz.common.client.ServiceList;
import com.qzl.lzshzz.common.exception.ExceptionCast;
import com.qzl.lzshzz.model.auth.request.LogRequest;
import com.qzl.lzshzz.model.ucenter.ext.AuthToken;
import com.qzl.lzshzz.model.ucenter.ext.SysAccountExt;
import com.qzl.lzshzz.model.ucenter.response.auth.AuthCode;
import com.qzl.lzshzz.ucenter.auth.client.UserClient;
import com.qzl.lzshzz.util.StringHelper;
import com.qzl.tools.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class AuthService {

    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserClient userClient;
    @Autowired
    SysLogService sysLogService;

    //用户认证申请令牌，将令牌存储到redis
    public AuthToken login(String username, String password, String isPhoneLogin, String deviceId, String loginType, String clientId, String clientSecret) {
        LogRequest logRequest = new LogRequest("", username, loginType, "", "", TimeHelper.getCurrentTime(), "");
        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(username, password, isPhoneLogin, deviceId, logRequest, clientId, clientSecret);
        if (authToken == null) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "账号或密码错误，登录失败！");
            //账号或密码错误 没有查到数据
            ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
        }
        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //存储到redis中的内容
        String jsonString = JSON.toJSONString(authToken);
        //将令牌存储到redis
        boolean result = this.saveToken(access_token, jsonString, tokenValiditySeconds);
        if (!result) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "存储令牌失败，登录失败！");
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVEFAIL);
        }
        return authToken;

    }
    //存储到令牌到redis

    /**
     * @param access_token 用户身份令牌
     * @param content      内容就是AuthToken对象的内容
     * @param ttl          过期时间
     * @return
     */
    private boolean saveToken(String access_token, String content, long ttl) {
        String key = "user_token:" + access_token;
        //只设置内容，不设置过期时间
//        stringRedisTemplate.boundValueOps(key).set(content);
        stringRedisTemplate.boundValueOps(key).set(content,ttl, TimeUnit.DAYS);
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire > 0 || expire == -1;
    }
    /**
     * @Author 强周亮
     * @Description redis刷新过期时间
     * @Date 18:29 2019/11/28
     **/
    public boolean refreshToken(String access_token, long ttl) {
        String key = "user_token:" + access_token;
        //刷新过期时间
        return stringRedisTemplate.expire(key, ttl, TimeUnit.DAYS);
    }
    //删除token
    public boolean delToken(String access_token) {
        String key = "user_token:" + access_token;
        stringRedisTemplate.delete(key);
        return true;
    }

    //从redis查询令牌
    public AuthToken getUserToken(String token) {
        String key = "user_token:" + token;
        //从redis中取到令牌信息
        String value = stringRedisTemplate.opsForValue().get(key);
        //转成对象
        try {
            AuthToken authToken = JSON.parseObject(value, AuthToken.class);
            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //申请令牌
    private AuthToken applyToken(String username, String password, String isPhoneLogin, String deviceId, LogRequest logRequest, String clientId, String clientSecret) {
        //从eureka中获取认证服务的地址（因为spring security在认证服务中）
        //从eureka中获取认证服务的一个实例的地址
        ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceList.SERVICE_UCENTER_AUTH);
        //此地址就是http://ip:port
        URI uri = serviceInstance.getUri();
        //令牌申请的地址 http://localhost:40400/auth/oauth/token
        String authUrl = uri + "/auth/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization", httpBasic);
        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        if ("1".equals(isPhoneLogin)) {
            //表示用账号手机号码登录，通过账号，手机号获取用户信息
            try {
                List<SysAccountExt> sysAccountExtList = userClient.getUserextByPhone(username, password);
                if (sysAccountExtList.size() > 1) {
                    saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "存在多个账号，登录失败！");
                    ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_EXISTS_MORE);
                }

                if (sysAccountExtList.size() == 0 || sysAccountExtList.get(0) == null || StringHelper.isEmptyString(sysAccountExtList.get(0).getUserAcctId())) {
                    //返回空给spring security表示用户不存在
                    saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "账号或手机号码错误，登录失败！");
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR_PHONE);
                }
                body.add("username", username + "," + sysAccountExtList.get(0).getLoginPassword());
                body.add("password", sysAccountExtList.get(0).getLoginPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            List<SysAccountExt> sysAccountExtList = userClient.getUserext(username, password);
            if (sysAccountExtList.size() > 1) {
                saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "存在多个账号，登录失败！");
                ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_EXISTS_MORE);
            }

            if (sysAccountExtList.size() == 0 || sysAccountExtList.get(0) == null || StringHelper.isEmptyString(sysAccountExtList.get(0).getUserAcctId())) {
                //返回空给spring security表示用户不存在
                saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "账号或密码错误，登录失败！");
                ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
            }
            body.add("username", username + "," + password);
            body.add("password", password);
        }

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables

        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);

        //申请令牌信息
        Map bodyMap = exchange.getBody();
        if (bodyMap == null ||
                bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null ||
                bodyMap.get("jti") == null) {

            //解析spring security返回的错误信息
            if (bodyMap != null && bodyMap.get("error_description") != null) {
                String error_description = (String) bodyMap.get("error_description");
                if (error_description.indexOf("UserDetailsService returned null") >= 0 || error_description.indexOf("坏的凭证") >= 0) {
                    saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "账号或密码错误，登录失败！");
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                } else if (error_description.indexOf("多个账号") > 0) {
                    saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "检测到存在多个账号，登录失败！");
                    ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_EXISTS_MORE);
                } else if (error_description.indexOf("设备id更新未成功") > 0) {
                    saveLog(logRequest, LogType.LOG_LOGIN_SUCCESS, "登录成功，设备ID更新失败！");
                    ExceptionCast.cast(AuthCode.AUTH_SAVE_DEVICE_ID_FAIL);
                }
            }
            return null;
        }
        AuthToken authToken = new AuthToken();
        authToken.setAccess_token((String) bodyMap.get("jti"));//用户身份令牌
        authToken.setRefresh_token((String) bodyMap.get("refresh_token"));//刷新令牌
        authToken.setJwt_token((String) bodyMap.get("access_token"));//jwt令牌
        return authToken;
    }


    //获取httpbasic的串
    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }

    /**
     * 发送短信
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @author 强周亮
     * @date 2019年1月5日 上午11:40:38
     */
    public String sendSms(String tel, String content) throws UnsupportedEncodingException {
        String strSmsParam = "reg=" + SmsSendInfo.strReg + "&pwd=" + SmsSendInfo.strPwd + "&sourceadd=&phone=" + tel + "&content=" + HttpSend.paraTo16(content);
        String dd = HttpSend.postSend("http://www.stongnet.com/sdkhttp/sendsms.aspx", strSmsParam);
        return dd.substring(0, 8);
    }

    private void saveLog(LogRequest logRequest, String logType, String logContent) {
        logRequest.setLogType(logType);
        logRequest.setLogContent(logContent);
        sysLogService.saveSysLog(logRequest);
    }
}
