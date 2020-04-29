package com.qzl.lzshzz.common.exception

import com.qzl.lzshzz.common.model.response.ResultCode


/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-14 17:31
 */
object ExceptionCast {

    @JvmStatic
    fun cast(resultCode: ResultCode) {
        throw CustomException(resultCode)
    }
}

//在调运的地方这样使用
//ExceptionCast.cast(resultCode)
