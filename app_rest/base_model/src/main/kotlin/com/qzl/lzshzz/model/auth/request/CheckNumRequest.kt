package com.qzl.lzshzz.model.auth.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 获取验证码请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "获取验证码请求参数")
class CheckNumRequest : CommonRequestData() {

    //登录用户名
    @ApiModelProperty(value = "用户名，若登录名为空，则不需要验证用户是否存在，直接发送验证码")
    var user: String = ""
    //登录密码
    @ApiModelProperty(value = "密码或电话号码", required = true)
    var tel: String = ""

}
