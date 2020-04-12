package com.zdww.lzshzz.model.auth.request


import com.zdww.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 发送通知请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "发送通知请求参数")
class SendNoticeRequest : CommonRequestData() {
    @ApiModelProperty(value = "用户别名，多个中间用，隔开", required = true)
    var alia: String = ""
    @ApiModelProperty(value = "通知内容", required = true)
    var content: String = ""
    @ApiModelProperty(value = "通知类型", required = true)
    var type: String = ""

}
