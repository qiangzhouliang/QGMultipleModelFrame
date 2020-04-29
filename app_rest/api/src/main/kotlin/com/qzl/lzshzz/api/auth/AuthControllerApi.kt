package com.qzl.lzshzz.api.auth

import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.model.auth.request.LoginRequest
import com.qzl.lzshzz.model.auth.request.LogoutRequest
import com.qzl.lzshzz.model.ucenter.response.GetUserInfoResult
import com.qzl.lzshzz.model.ucenter.response.LoginResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * 用户认证接口定义
 */
@Api(value = "用户认证", description = "用户认证接口")
interface AuthControllerApi {
    @ApiOperation("登录")
    fun login(loginRequest: LoginRequest): LoginResult

    @ApiOperation("自动登录")
    fun autoLogin(loginRequest: LoginRequest): LoginResult

    @ApiOperation("退出")
    fun logout(logoutRequest: LogoutRequest): ResponseResult

    @ApiOperation("获取用户信息")
    fun getUserInfo(loginRequest: LoginRequest): GetUserInfoResult?
}
