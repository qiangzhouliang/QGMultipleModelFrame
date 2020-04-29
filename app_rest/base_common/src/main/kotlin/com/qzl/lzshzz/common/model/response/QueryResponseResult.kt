package com.qzl.lzshzz.common.model.response

import lombok.Data
import lombok.ToString

@Data
@ToString
class QueryResponseResult(resultCode: ResultCode, var queryResult: QueryResult<*>) : ResponseResult(resultCode)
