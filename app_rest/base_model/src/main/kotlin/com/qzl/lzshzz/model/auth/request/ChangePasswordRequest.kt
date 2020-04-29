package com.qzl.lzshzz.model.auth.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 修改密码请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "修改密码请求参数")
class ChangePasswordRequest : CommonRequestData() {

    //登录用户名
    @ApiModelProperty(value = "用户名", required = true)
    var userName: String = ""
    //登录密码
    @ApiModelProperty(value = "旧密码", required = true)
    var oldPwd: String = ""
    @ApiModelProperty(value = "新密码", required = true)
    var newPwd: String = ""
    @ApiModelProperty(value = "1 表示用用户名手机号登陆 0 表示用账号密码登录,默认0", required = true)
    var phoneLoginFlag: String = ""

}
