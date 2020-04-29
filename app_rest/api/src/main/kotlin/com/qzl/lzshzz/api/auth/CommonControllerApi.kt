package com.qzl.lzshzz.api.auth

import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.model.auth.Apk
import com.qzl.lzshzz.model.auth.request.*
import com.qzl.lzshzz.model.ucenter.response.CheckNumResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * 公共调运方法的接口
 */
@Api(value = "公共调运方法接口", description = "公共调运方法接口")
interface CommonControllerApi {
    @ApiOperation("删除redis信息")
    fun deleteRedis(uid: String): ResponseResult?

    @ApiOperation("获取验证码")
    fun getCheckNum(checkNumRequest: CheckNumRequest): CheckNumResult?

    @ApiOperation("验证验证码")
    fun checkNum(checkNumRequest: CheckCheckNumRequest): CheckNumResult?

    @ApiOperation("发送短信接口")
    fun sendSms(request: SendSmsRequest): ResponseResult?

    @ApiOperation("发送通知")
    fun sendNotice(request: SendNoticeRequest): ResponseResult?

    @ApiOperation("apk版本更新")
    fun getApkVersion(request: GetApkVersionRequest): Apk?
}
