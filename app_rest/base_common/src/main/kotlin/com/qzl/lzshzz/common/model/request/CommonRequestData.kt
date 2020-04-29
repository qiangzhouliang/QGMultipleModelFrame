package com.qzl.lzshzz.common.model.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import lombok.ToString
import javax.persistence.Transient

/**
 * @author 强周亮
 * @desc 请求基础类
 * @email 2538096489@qq.com
 * @time 2019/11/7 11:06
 */
@Data
@ToString
@ApiModel(value = "请求公共类")
open class CommonRequestData(
        @ApiModelProperty(value = "添加请求token")
        @get:Transient
        var uid: String? = "",
        @ApiModelProperty(value = "用户账号ID")
        @get:Transient
        var userAcctId: String = "",
        @ApiModelProperty(value = "登录用户账号")
        @get:Transient
        var loginAccount: String = "",
        @ApiModelProperty(value = "设备类型 android 或 ios")
        @get:Transient
        var driveType: String = "")
