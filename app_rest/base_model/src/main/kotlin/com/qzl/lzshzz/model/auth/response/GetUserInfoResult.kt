package com.qzl.lzshzz.model.ucenter.response


import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.model.response.ResultCode
import com.qzl.lzshzz.model.ucenter.ext.SysAccountExt

/**
 * Created by mrt on 2018/5/21.
 */
data class GetUserInfoResult(private var resultCode: ResultCode, var data: SysAccountExt? = null) : ResponseResult(resultCode)
