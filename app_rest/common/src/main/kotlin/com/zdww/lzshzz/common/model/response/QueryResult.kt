package com.zdww.lzshzz.common.model.response

import lombok.Data
import lombok.ToString

@Data
@ToString
class QueryResult<T> {
    //数据列表
    var list: List<T>? = null
    //数据总数
    var total: Long = 0
}
