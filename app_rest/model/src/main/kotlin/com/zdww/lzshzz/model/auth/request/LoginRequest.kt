package com.zdww.lzshzz.model.auth.request


import com.zdww.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 登录请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "登录请求参数")
class LoginRequest : CommonRequestData() {

    //登录用户名
    @ApiModelProperty(value = "登录用户名", required = true)
    var user: String = ""
    //登录密码
    @ApiModelProperty(value = "登录密码或电话号码", required = true)
    var userMd5: String = ""
    @ApiModelProperty(value = "登录类型（Android-登录 或 iOS-登录）", required = true)
    var loginType: String = "app-登录"

    @ApiModelProperty(value = "设备id")
    var deviceId: String = ""

    @ApiModelProperty(value = "1 表示用用户名手机号登陆 0 表示用账号密码登录,默认0", required = true)
    var phoneLoginFlag: String = ""
    @ApiModelProperty(value = "用户验证码")
    var checkNumFlag: String = ""

    @ApiModelProperty(value = "是否需要验证验证码 0 不需要 1 需要")
    var checkNum: String = "0"

}
