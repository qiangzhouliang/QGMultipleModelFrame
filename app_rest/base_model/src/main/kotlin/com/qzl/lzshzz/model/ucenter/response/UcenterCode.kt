package com.qzl.lzshzz.model.ucenter.response

import com.google.common.collect.ImmutableMap
import com.qzl.lzshzz.common.model.response.ResultCode
import io.swagger.annotations.ApiModelProperty
/**
 * @author 强周亮
 * @desc 用户中心返回结果代码
 * @email 2538096489@qq.com
 * @time 2019/11/20 13:57
 */
enum class UcenterCode(//操作代码
        @field:ApiModelProperty(value = "操作是否成功", example = "true", required = true)
        internal var success: Boolean, //操作代码
        @field:ApiModelProperty(value = "操作代码", example = "22001", required = true)
        internal var code: Int, //提示信息
        @field:ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
        internal var message: String) : ResultCode {
    UCENTER_CHANGE_PWD_SUCCESS(true, 22001, "密码修改成功,请重新登录！"),
    UCENTER_CHANGE_PWD_FAIL(false, 22002, "密码修改失败，请重新修改！"),
    UCENTER_VERIFYCODE_NONE(false, 22003, "请输入验证码！"),
    UCENTER_ACCOUNT_NOTEXISTS(false, 22004, "账号不存在！"),
    UCENTER_CREDENTIAL_ERROR(false, 22005, "账号或密码错误！"),
    UCENTER_LOGIN_ERROR(false, 22006, "登陆过程出现异常请尝试重新操作！");

    override fun success(): Boolean {
        return success
    }

    override fun code(): Int {
        return code
    }

    override fun message(): String {
        return message
    }

    companion object {
        private val CACHE: ImmutableMap<Int, UcenterCode>

        init {
            val builder = ImmutableMap.builder<Int, UcenterCode>()
            for (commonCode in values()) {
                builder.put(commonCode.code(), commonCode)
            }
            CACHE = builder.build()
        }
    }
}
