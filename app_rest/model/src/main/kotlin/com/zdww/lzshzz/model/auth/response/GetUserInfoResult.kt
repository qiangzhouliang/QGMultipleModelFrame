package com.zdww.lzshzz.model.ucenter.response


import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.model.response.ResultCode
import com.zdww.lzshzz.model.ucenter.ext.SysAccountExt

/**
 * Created by mrt on 2018/5/21.
 */
data class GetUserInfoResult(private var resultCode: ResultCode, var data: SysAccountExt? = null) : ResponseResult(resultCode)
