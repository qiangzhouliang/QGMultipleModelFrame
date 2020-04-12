package com.zdww.lzshzz.model.sys

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import javax.persistence.*

/**
 * The persistent class for the sys_districts database table.
 *
 */
@Entity
@Table(name = "sys_districts")
@ApiModel("行政区划模型")
class SysDistrict : Serializable{
    @Id
    @get:Column(name = "RES_ID")
    var resId: String? = null
    @get:Column(name = "LAT")
    var lat: String? = null
    @get:Column(name = "LNG")
    var lng: String? = null
    @get:Column(name = "REMARK")
    var remark: String? = null
    @get:Column(name = "RES_LEVEL")
    var resLevel: String? = null
    @get:Column(name = "RES_NAME")
    var resName: String? = null
    @get:Column(name = "RES_PART_ID")
    var resPartId: String? = null
    @get:Column(name = "SORT_CODE")
    var sortCode: String? = null
    @get:Column(name = "STATE")
    var state: String? = null
    @get:Column(name = "RES_ENAME")
    var resEname: String? = null
    @get:Column(name = "STRUCT_FULLNAME")
    var structFullname: String? = null
    @get:Column(name = "UPDATE_TIME")
    var updateTime: String? = null
    //统计数据
    @ApiModelProperty("行政区划额外数值")
    @Transient
    var cun: String? = null
}