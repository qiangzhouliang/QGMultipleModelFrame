package com.qzl.lzshzz.model.auth.response

/**
 * @author 强周亮
 * @desc 登录成功后返回的数据
 * @email 2538096489@qq.com
 * @time 2019/11/1 9:29
 */
class LoginResultData(var token: String?, var jwtToken: String?, var refreshToken: String?) {}