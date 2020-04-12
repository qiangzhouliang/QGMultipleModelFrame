package com.zdww.lzshzz.model.ucenter.response


import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.model.response.ResultCode

/**
 * Created by mrt on 2018/5/21.
 */
data class JwtResult(private var resultCode: ResultCode, val jwt: String?) : ResponseResult(resultCode)
