package com.zdww.lzshzz.model.auth.request


import com.zdww.tools.utils.TimeHelper
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 保存日志请求类
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "保存日志请求参数")
data class LogRequest(
        @ApiModelProperty(value = "用户账号ID", required = true)
        var userAcctId: String? = "",
        @ApiModelProperty(value = "登录用户账号", required = true)
        var loginAccount: String? = "",
        @ApiModelProperty(value = "功能操作类型")
        var logModel: String,
        @ApiModelProperty(value = "日志类型,1:登陆日志（1-1：登录成功；1-2：登陆失败），2：操作日志(2-1:新增；2-2：编辑；2-3：删除)，3：错误日志（3-1:新增；3-2：编辑；3-3：删除）")
        var logType: String = "",
        //日志内容
        @ApiModelProperty(value = "日志内容")
        var logContent: String = "",
        //操作时间
        @ApiModelProperty(value = "操作时间")
        var operatorTime: String? = TimeHelper.currentTime,
        //IP地址
        @ApiModelProperty(value = "IP地址")
        var logIp: String? = ""
)
