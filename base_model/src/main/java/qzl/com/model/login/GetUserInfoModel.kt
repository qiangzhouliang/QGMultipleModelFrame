package qzl.com.model.login

import qzl.com.model.user_info.UserInfo

/**
 * @desc 登录成功后解析model
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/11/1 14:15
 * @class lzshzz_android
 * @package com.zdww.loginmodel.model
 */
data class GetUserInfoModel(
    val code: Int,
    val `data`: UserInfo,
    val message: String,
    val success: Boolean
)