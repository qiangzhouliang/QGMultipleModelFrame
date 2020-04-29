package com.qzl.lzshzz.model.auth.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 发送短信请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "发送短信请求参数")
class SendSmsRequest : CommonRequestData() {
    //验证码
    @ApiModelProperty(value = "短信内容")
    var content: String = ""
    //登录密码
    @ApiModelProperty(value = "电话号码", required = true)
    var tel: String = ""

}
