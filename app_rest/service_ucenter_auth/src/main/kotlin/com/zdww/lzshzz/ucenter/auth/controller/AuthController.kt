package com.zdww.lzshzz.ucenter.auth.controller

import com.zdww.lzshzz.api.auth.AuthControllerApi
import com.zdww.lzshzz.common.Constants
import com.zdww.lzshzz.common.client.LogType
import com.zdww.lzshzz.common.exception.ExceptionCast
import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.web.BaseController
import com.zdww.lzshzz.model.auth.request.LogRequest
import com.zdww.lzshzz.model.auth.request.LoginRequest
import com.zdww.lzshzz.model.auth.request.LogoutRequest
import com.zdww.lzshzz.model.auth.response.LoginResultData
import com.zdww.lzshzz.model.ucenter.SysAccount
import com.zdww.lzshzz.model.ucenter.ext.SysAccountExt
import com.zdww.lzshzz.model.ucenter.request.GetDistrictInfoRequest
import com.zdww.lzshzz.model.ucenter.response.GetUserInfoResult
import com.zdww.lzshzz.model.ucenter.response.LoginResult
import com.zdww.lzshzz.model.ucenter.response.auth.AuthCode
import com.zdww.lzshzz.ucenter.auth.client.InfoManageClient
import com.zdww.lzshzz.ucenter.auth.client.UserClient
import com.zdww.lzshzz.ucenter.auth.service.AuthService
import com.zdww.lzshzz.ucenter.auth.service.RedisService
import com.zdww.lzshzz.ucenter.auth.service.SysLogService
import com.zdww.lzshzz.util.CookieUtil
import com.zdww.lzshzz.util.JPushClientUtil
import com.zdww.lzshzz.util.StringHelper
import org.apache.commons.lang.StringUtils
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*


/**
 * @author Administrator
 * @version 1.0
 */
@RestController
class AuthController : BaseController(), AuthControllerApi {
    @Value("\${auth.clientId}")
    internal var clientId: String? = null
    @Value("\${auth.clientSecret}")
    internal var clientSecret: String? = null
    @Value("\${auth.cookieDomain}")
    internal var cookieDomain: String? = null
    @Value("\${auth.cookieMaxAge}")
    internal var cookieMaxAge: Int = 0
    @Value("\${auth.tokenValiditySeconds}")
    var tokenValiditySeconds = 0

    @Autowired
    lateinit var authService: AuthService
    @Autowired
    lateinit var userClient: UserClient
    //    日志操作服务类
    @Autowired
    lateinit var sysLogService: SysLogService

    @Autowired
    lateinit var redisService: RedisService
    //信息管理调运客户端
    @Autowired
    lateinit var infoManageClient: InfoManageClient

    //取出cookie中的身份令牌
    private val tokenFormCookie: String?
        get() {
            val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            val map = CookieUtil.readCookie(request, "uid")
            return if (map["uid"] != null) {
                map["uid"]
            } else null
        }

    /**
     * @Author 强周亮
     * @Description 用户登录信息
     * @Date 10:26 2019/11/4
     **/
    @PostMapping("/userlogin")
    override fun login(@RequestBody loginRequest: LoginRequest): LoginResult {
        val logRequest = LogRequest(loginRequest.userAcctId, loginRequest.user, loginRequest.loginType)
        if (!StringHelper.isEmptyString(loginRequest.uid)) {
            //查询redis中是否存在token
            val userToken = authService.getUserToken(loginRequest.uid)
            if (userToken != null) {
                authService.delToken(loginRequest.uid)
            }
        }
        if (StringUtils.isEmpty(loginRequest.user)) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "登录用户名为空，登录失败！")
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE)
        }
        if (StringUtils.isEmpty(loginRequest.userMd5)) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "登录密码为空，登录失败！")
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE)
        }
        //账号
        val username = loginRequest.user
        //密码
        val password = loginRequest.userMd5
        //限制登录次数
        //从Redis里面获取登录次数
        var loginNum = redisService.getLoginNum(username)
        if (loginNum > Constants.LOGIN_NUM) {
            return LoginResult(CommonCode.CHECK_NUM_MORE, checkTip = "检测到您输入错误密码的次数过多，请您" + redisService.getRedisTime(username).plus(1).toString() + "分钟以后再登录")
        } else {
            loginNum += 1
            redisService.saveData(username, StringHelper.toString(loginNum), Constants.REDIS_DEAD_LINE)
        }
        //判断验证码
        if ("1" == loginRequest.phoneLoginFlag && "1" == loginRequest.checkNumFlag) {
            //表示用账号手机号码登录，通过账号，手机号获取用户信息
            //判断验证码是否正常
            val checkNumRedis = redisService.getData(password)
            if (StringHelper.isEmptyString(loginRequest.checkNum) || StringHelper.isEmptyString(checkNumRedis) || loginRequest.checkNum != checkNumRedis) {
                return LoginResult(AuthCode.AUTH_CHECK_NUM_FAIL)
            }
        }
        //申请令牌
        val authToken = authService.login(username, password, loginRequest.phoneLoginFlag, loginRequest.deviceId, loginRequest.loginType, clientId, clientSecret)

        //用户身份令牌
        val access_token = authToken?.access_token
        // jwt令牌
        val jwtToken = authToken.jwt_token
        //刷新令牌
        val refreshToken = authToken.refresh_token
        //将令牌存储到cookie
        this.saveCookie(access_token)
        // 获取到的数据需要解密，jwt_token中的三部分内容是通过.来连接的，使用的时候讲中间内容部分用base64解码皆可以得到想要的值
//        val substring = jwtToken.substring(jwtToken.indexOf("."), jwtToken.lastIndexOf("."))
//        val encodeBase64 = Base64.decodeBase64(substring.toByteArray(charset("UTF-8")))
//        println("RESULT: " + String(encodeBase64))

        saveLog(logRequest, LogType.LOG_LOGIN_SUCCESS, "登录成功！")

        //删除登录测试和验证码
        redisService.delLoginNum(username)
        redisService.delLoginNum(password)
        return LoginResult(CommonCode.SUCCESS, LoginResultData(access_token, jwtToken, refreshToken))
    }

    /**
     * @Author 强周亮
     * @Description 自动登录
     * @Date 10:26 2019/11/4
     **/
    @PostMapping("/autoLogin")
    override fun autoLogin(@RequestBody loginRequest: LoginRequest): LoginResult {
        val (sysAccountExt, saveUserInfo, logRequest) = getSysAccountExt(loginRequest, "-自动")

        authService.refreshToken(loginRequest.uid, tokenValiditySeconds.toLong())
        return if (saveUserInfo != null) {
            saveLog(logRequest, LogType.LOG_LOGIN_SUCCESS, "自动登录成功！")
            //更新成功
            LoginResult(CommonCode.SUCCESS)
        } else {
            saveLog(logRequest, LogType.LOG_LOGIN_SUCCESS, "自动登录成功，更新设备id失败！")
            // 更新失败
            LoginResult(AuthCode.AUTH_SAVE_DEVICE_ID_FAIL)
        }
    }

    /**
     * @Author 强周亮
     * @Description 保存日志
     * @Date 15:00 2019/11/7
     **/
    private fun saveLog(logRequest: LogRequest, logType: String, logContent: String) {
        logRequest.logType = logType
        logRequest.logContent = logContent
        sysLogService.saveSysLog(logRequest)
    }

    //将令牌存储到cookie
    private fun saveCookie(token: String?) {

        val response = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).response
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false)

    }

    //从cookie删除token
    private fun clearCookie(token: String?) {

        val response = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).response
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false)

    }

    //退出
    @PostMapping("/userlogout")
    override fun logout(@RequestBody logoutRequest: LogoutRequest): ResponseResult {
        var uid = logoutRequest.uid
        //如果没有 则从cookie中获取
        if (StringUtils.isEmpty(uid)) {
            //取出cookie中的用户身份令牌
            uid = tokenFormCookie
        }
        //删除redis中的token
        val result = authService.delToken(uid)
        //清除cookie
        this.clearCookie(uid)
        //删除用户账号中的设备id
        logoutRequest.userAcctId.let {
            val accountInfo = userClient.getAccountInfo(it)
            if (accountInfo != null) {
                accountInfo.deviceId = ""
            }
            if (accountInfo != null) {
                userClient.saveSysAccount(accountInfo)
            }
        }
        return ResponseResult(CommonCode.SUCCESS)
    }

    //获取用户信息
    @PostMapping("/getUserInfo")
    override fun getUserInfo(@RequestBody loginRequest: LoginRequest): GetUserInfoResult? {
        val (sysAccountExt, saveUserInfo, logRequest) = getSysAccountExt(loginRequest)

        //设置权限信息 1 部门信息、2 河长信息、3 河长头像、4 用户角色信息
        // 1 部门信息
        val deptInfo = userClient.getDeptInfo(sysAccountExt.deptId!!)
        sysAccountExt.department = deptInfo

        // 2 河长信息
        val baseRiverHead = userClient.getBaseRiverHead(sysAccountExt.userAcctId)
        sysAccountExt.basRiverHead = baseRiverHead

        // 3 河长头像
        if (baseRiverHead != null && !StringHelper.isEmptyString(baseRiverHead.riverHeadId)) {
            val headImage = userClient.getHeadImage(baseRiverHead.riverHeadId!!)
            if (headImage != null && headImage.isNotEmpty()) {
                val imageList = ArrayList<String>()
                for (hImage in headImage) {
                    imageList.add(hImage.fileUrl + "/" + hImage.fileNewName)
                }
                sysAccountExt.headImage = imageList
            }
        }

        // 4 用户角色信息
        val sysRoleInfo = userClient.getSysRoleInfo(sysAccountExt.userAcctId)
        if (sysRoleInfo != null && sysRoleInfo.isNotEmpty()) {
            val sb = StringBuffer()
            for (role in sysRoleInfo) {
                sb.append(role.roleId!! + ",")
            }
            sysAccountExt.roleId = sb.toString()
        }

        // 5 行政区划名称
        val request = GetDistrictInfoRequest()
        request.areaCode = sysAccountExt.areaCode?:""
        val districtInfo = userClient.getDistrictInfo(request)
        districtInfo?.let {
            sysAccountExt.areaName = districtInfo.resName
            sysAccountExt.areaLevel = districtInfo.resLevel
        }

        return if (saveUserInfo != null) {
            //更新成功
            saveLog(logRequest, LogType.LOG_LOGIN_SUCCESS, "登录成功！")
            GetUserInfoResult(CommonCode.SUCCESS, sysAccountExt)
        } else {
            // 更新失败
            saveLog(logRequest, LogType.LOG_LOGIN_SUCCESS, "登录成功，更新设备id失败！")
            GetUserInfoResult(AuthCode.AUTH_SAVE_DEVICE_ID_FAIL, sysAccountExt)
        }
    }

    //获取用户信息
    private fun getSysAccountExt(loginRequest: LoginRequest, cot: String = ""): Triple<SysAccountExt, SysAccount?, LogRequest> {
        val logRequest = LogRequest(loginRequest.userAcctId, loginRequest.loginAccount, loginRequest.loginType + cot)
        if (StringUtils.isEmpty(loginRequest.user)) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "登录用户名为空，${cot}登录失败！")
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE)
        }
        if (StringUtils.isEmpty(loginRequest.userMd5)) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "登录密码为空，${cot}登录失败！")
            sysLogService.saveSysLog(logRequest)
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE)
        }
        var authCode = AuthCode.AUTH_CREDENTIAL_ERROR
        val sysAccountExtList = if (loginRequest.phoneLoginFlag == "0") {
            //用户名 密码登录
            userClient.getUserext(loginRequest.user, loginRequest.userMd5)
        } else {
            //表示用账号手机号码登录，通过账号，手机号获取用户信息
            authCode = AuthCode.AUTH_ACCOUNT_EXISTS_MORE
            userClient.getUserextByPhone(loginRequest.user, loginRequest.userMd5)
        }
        if (sysAccountExtList.size > 1) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "检查到系统存在多个账号，${cot}登录失败！")
            ExceptionCast.cast(authCode)
        }
        val sysAccountExt = sysAccountExtList[0]
        if (sysAccountExtList.isEmpty() || sysAccountExt.userAcctId == null || StringHelper.isEmptyString(sysAccountExt.userAcctId)) {
            saveLog(logRequest, LogType.LOG_LOGIN_FAIL, "未找到用户信息，${cot}登录失败！")
            ExceptionCast.cast(authCode)
        }

        //判断设备id，如果不是同一设备，给原先的设备发送下线通知
        val deviceId = sysAccountExt.deviceId
        if (!StringHelper.isEmptyString(deviceId) && deviceId != loginRequest.deviceId) {
            //1.极光推送通知之前登录的另一台设备下线。
            JPushClientUtil.pushOffLineToApp(deviceId)
        }
        //更新设备id
        val sysAccount = SysAccount()
        //将sysAccountExt的内容拷贝给sysAccount
        BeanUtils.copyProperties(sysAccountExt, sysAccount)
        sysAccount.deviceId = loginRequest.deviceId
        val saveUserInfo = userClient.saveSysAccount(sysAccount)
        return Triple(sysAccountExt, saveUserInfo, logRequest)
    }
}