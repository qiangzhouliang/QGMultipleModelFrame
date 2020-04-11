package qzl.com.model.login

/**
 * @desc 登录成功后解析model
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/11/1 14:15
 * @class lzshzz_android
 * @package com.zdww.loginmodel.model
 */
data class LoginModel(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean,
    val checkTip:String
)
data class Data(
    val jwtToken: String,
    val refreshToken: String,
    val token: String
)