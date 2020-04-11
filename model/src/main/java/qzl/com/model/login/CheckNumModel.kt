package qzl.com.model.login

/**
 * @desc 获取验证码成功后model
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/11/1 14:15
 * @class lzshzz_android
 * @package com.zdww.loginmodel.model
 */
data class CheckNumModel(
    val code: Int,
    //验证码有效期
    val yxq: Double,
    val checkNum: String,
    val message: String,
    val success: Boolean
)