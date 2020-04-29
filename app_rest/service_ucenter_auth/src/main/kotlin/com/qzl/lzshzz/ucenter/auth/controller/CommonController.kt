package com.qzl.lzshzz.ucenter.auth.controller

import com.qzl.lzshzz.api.auth.CommonControllerApi
import com.qzl.lzshzz.common.Constants
import com.qzl.lzshzz.common.exception.ExceptionCast
import com.qzl.lzshzz.common.model.response.CommonCode
import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.web.BaseController
import com.qzl.lzshzz.model.auth.Apk
import com.qzl.lzshzz.model.auth.request.*
import com.qzl.lzshzz.model.ucenter.response.CheckNumResult
import com.qzl.lzshzz.model.ucenter.response.auth.AuthCode
import com.qzl.lzshzz.ucenter.auth.client.UserClient
import com.qzl.lzshzz.ucenter.auth.service.AuthService
import com.qzl.lzshzz.ucenter.auth.service.CommonService
import com.qzl.lzshzz.ucenter.auth.service.RedisService
import com.qzl.lzshzz.util.CheckNumUtil
import com.qzl.lzshzz.util.JPushClientUtil
import com.qzl.lzshzz.util.StringHelper
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.UnsupportedEncodingException
import java.util.*

/**
 * @author 强周亮
 * @desc 公共发方法controller，可能需要访问外网，部署时要注意
 * @email 2538096489@qq.com
 * @time 2019/11/28 11:17
 */
@RestController
class CommonController : BaseController(), CommonControllerApi {
    @Autowired
    lateinit var authService: AuthService
    @Autowired
    lateinit var userClient: UserClient

    @Autowired
    lateinit var redisService: RedisService
    @Autowired
    lateinit var commonService: CommonService

    @GetMapping("/deleteRedis")
    override fun deleteRedis(@RequestParam("uid") uid: String): ResponseResult? {
        if (StringUtils.isEmpty(uid)) {
            //取出cookie中的用户身份令牌
            return ResponseResult(CommonCode.FAIL)
        }
        //删除redis中的token
        val result = authService.delToken(uid)
        return if (result) {
            ResponseResult(CommonCode.SUCCESS)
        } else {
            ResponseResult(CommonCode.FAIL)
        }
    }

    /**
     * @Author 强周亮
     * @Description 获取短信验证码
     * @Date 16:54 2019/11/4
     **/
    @GetMapping("/getCheckNum")
    override fun getCheckNum(checkNumRequest: CheckNumRequest): CheckNumResult? {
        val checkNum = CheckNumUtil.random
        val content = "您的动态验证密码是：" + checkNum + "二分钟内有效，请勿把验证密码泄露给其他人，如非本人，请勿操作。" + Constants.SMS_CONSTRAINT
        return try {
            //检查电话号码是否合法
            if (!StringHelper.checkPhone(checkNumRequest.tel)) {
                ExceptionCast.cast(CommonCode.FFATL_PHONE_ERROR)
            }
            if (checkNumRequest.user != "") {
                //表示需要判断用户名是否存在
                val sysAccountExtList = userClient.getUserextByPhone(checkNumRequest.user, checkNumRequest.tel)
                if (sysAccountExtList.size > 1) {
                    ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_EXISTS_MORE)
                }

                if (sysAccountExtList.isEmpty() || sysAccountExtList[0] == null || sysAccountExtList[0].userAcctId == null || StringHelper.isEmptyString(sysAccountExtList[0].userAcctId)) {
                    //返回空给spring security表示用户不存在
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR_PHONE)
                }
            }
            val sendSms = authService.sendSms(checkNumRequest.tel, content)
            redisService.saveData(checkNumRequest.tel, checkNum, 125)
            //验证码有效期在后台控制
            CheckNumResult(AuthCode.AUTH_CHECKED_SUCCESS)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            CheckNumResult(AuthCode.AUTH_CHECKED_FAIL)
        }
    }

    //检验验证码
    @GetMapping("/checkNum")
    override fun checkNum(checkCheckNumRequest: CheckCheckNumRequest): CheckNumResult? {
        //将验证码保存到redis中
        val redisCheckNum = redisService.getData(checkCheckNumRequest.tel)
        return if (StringHelper.isEmptyString(checkCheckNumRequest.checkNum) || StringHelper.isEmptyString(redisCheckNum) || checkCheckNumRequest.checkNum != redisCheckNum) {
            CheckNumResult(AuthCode.AUTH_CHECK_NUM_FAIL)
        } else {
            val userextByPhone = userClient.getUserextByPhone(checkCheckNumRequest.loginAccount, checkCheckNumRequest.tel)
            if (userextByPhone.size > 1){
                CheckNumResult(AuthCode.AUTH_ACCOUNT_EXISTS_MORE)
            } else if (userextByPhone.isEmpty() || userextByPhone[0].userAcctId == null || StringHelper.isEmptyString(userextByPhone[0].userAcctId)) {
                CheckNumResult(AuthCode.AUTH_CREDENTIAL_ERROR_USER_PHONE)
            } else {
                CheckNumResult(AuthCode.AUTH_CHECK_NUM_SUCCESS)
            }
        }
    }
    /**
     * @Author 强周亮
     * @Description 发送短信
     * @Date 11:51 2019/11/28
     **/
    @GetMapping("/sendSms")
    override fun sendSms(request: SendSmsRequest): ResponseResult? {
        return try {
            if (StringHelper.checkPhone(request.tel)) {
                val sendSms = authService.sendSms(request.tel, request.content+ Constants.SMS_CONSTRAINT)
                ResponseResult(CommonCode.SUCCESS)
            } else {
                ResponseResult(CommonCode.FFATL_PHONE_ERROR)
            }
        } catch (e:Exception){
            ResponseResult(CommonCode.FAIL)
        }
    }

    /**
     * @Author 强周亮
     * @Description 发送通知请求参数
     * @Date 12:00 2019/11/28
     **/
    @PostMapping("sendNotice")
    override fun sendNotice(request: SendNoticeRequest): ResponseResult? {
        return try {
            val strArr = request.alia.split(",")
            val aliases: MutableCollection<String> = ArrayList()
            strArr.forEach {
                if (!StringHelper.isEmptyString(it)) {
                    aliases.add(it)
                }
            }
            JPushClientUtil.pushEventNoticeToApp(aliases, request.content, request.type)
            ResponseResult(CommonCode.SUCCESS)
        } catch (e: java.lang.Exception) {
            ResponseResult(CommonCode.FAIL)
        }
    }

    //获取apk版本信息
    @GetMapping("getApkVersion")
    override fun getApkVersion(request: GetApkVersionRequest): Apk? {
        return commonService.getApkInfo(request)
    }
}