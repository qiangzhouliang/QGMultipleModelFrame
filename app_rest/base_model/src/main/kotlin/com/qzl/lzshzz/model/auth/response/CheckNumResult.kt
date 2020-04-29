package com.qzl.lzshzz.model.ucenter.response


import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.model.response.ResultCode

/**
 * 获取验证码返回结果bean
 */
data class CheckNumResult(private var resultCode: ResultCode, var checkNum: String = "", var yxq: Double = 120.0) : ResponseResult(resultCode)
