package com.qzl.lzshzz.common.model.response


class QueryResponseFeignResult(var success: Boolean, var code: Int, var message: String, var queryResult: QueryResult<*>)
