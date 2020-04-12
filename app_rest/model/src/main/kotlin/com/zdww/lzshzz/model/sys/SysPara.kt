package com.zdww.lzshzz.model.sys

import java.io.Serializable
import javax.persistence.*

/**
 * 参数
 * @author zhangjy
 */
@Entity
@Table(name = "SYS_PARAMETER")
class SysPara : Serializable {
    @get:Column(name = "PARA_ID")
    @get:Id
    var paraId: String? = null
    @get:Column(name = "PARA_TYPE_ID")
    var paraTypeId: String? = null
    @get:Column(name = "PARA_CODE")
    var paraCode: String? = null
    @get:Column(name = "PARA_NAME")
    var paraName: String? = null
    @get:Column(name = "PARA_SEQ")
    var paraSeq: Int? = null
    @get:Column(name = "PARA_STATE")
    var paraState: String? = null
}