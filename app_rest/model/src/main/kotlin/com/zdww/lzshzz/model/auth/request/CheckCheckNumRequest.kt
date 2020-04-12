package com.zdww.lzshzz.model.auth.request


import com.zdww.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 校验验证码请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "获取验证码请求参数")
class CheckCheckNumRequest : CommonRequestData() {

    //验证码
    @ApiModelProperty(value = "验证码")
    var checkNum: String = ""
    //登录密码
    @ApiModelProperty(value = "电话号码", required = true)
    var tel: String = ""

}
