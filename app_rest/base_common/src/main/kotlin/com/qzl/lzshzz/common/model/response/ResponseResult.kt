package com.qzl.lzshzz.common.model.response

import lombok.Data
import lombok.NoArgsConstructor
import lombok.ToString

@Data
@ToString
@NoArgsConstructor
open class ResponseResult : Response {

    //操作是否成功
    var success = Response.SUCCESS

    //操作代码
    var code = Response.SUCCESS_CODE

    //提示信息
    var message: String

    constructor(resultCode: ResultCode) {
        this.success = resultCode.success()
        this.code = resultCode.code()
        this.message = resultCode.message()
    }

    companion object {

        fun SUCCESS(): ResponseResult {
            return ResponseResult(CommonCode.SUCCESS)
        }

        fun FAIL(): ResponseResult {
            return ResponseResult(CommonCode.FAIL)
        }
    }

}
