package com.qzl.lzshzz.common.exception

import com.qzl.lzshzz.common.model.response.ResultCode

/**
 * 自定义异常类型
 * @author Administrator
 * @version 1.0
 * @create 2018-09-14 17:28
 */
class CustomException(resultCode: ResultCode) : RuntimeException() {

    //错误代码
    var resultCode: ResultCode
        internal set

    init {
        this.resultCode = resultCode
    }


}
