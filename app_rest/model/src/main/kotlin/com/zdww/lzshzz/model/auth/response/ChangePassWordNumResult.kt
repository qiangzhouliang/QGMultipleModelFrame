package com.zdww.lzshzz.model.ucenter.response


import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.model.response.ResultCode

/**
 * 修改密码返回结果
 */
data class ChangePassWordNumResult(private var resultCode: ResultCode) : ResponseResult(resultCode)
