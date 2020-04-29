package com.qzl.lzshzz.model.ucenter.response.auth

import com.google.common.collect.ImmutableMap
import com.qzl.lzshzz.common.model.response.ResultCode
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 用户认证返回结果代码
 * @email 2538096489@qq.com
 * @time 2019/11/20 13:56
 */
enum class AuthCode(//操作代码
        @field:ApiModelProperty(value = "操作是否成功", example = "true", required = true)
        internal var success: Boolean, //操作代码
        @field:ApiModelProperty(value = "操作代码", example = "22001", required = true)
        internal var code: Int, //提示信息
        @field:ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
        internal var message: String) : ResultCode {
    AUTH_USERNAME_NONE(false, 23001, "请输入账号！"),
    AUTH_PASSWORD_NONE(false, 23002, "请输入密码！"),
    AUTH_VERIFYCODE_NONE(false, 23003, "请输入验证码！"),
    AUTH_CREDENTIAL_ERROR_USER_PHONE(false, 23004, "账号或手机号码错误"),
    AUTH_CREDENTIAL_ERROR(false, 23005, "账号或密码错误，总共有6次机会哦！"),
    AUTH_CREDENTIAL_ERROR_PHONE(false, 23009, "账号或手机号码错误，总共有6次机会哦！！"),
    AUTH_LOGIN_ERROR(false, 23006, "登陆过程出现异常请尝试重新操作！"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(false, 23007, "申请令牌失败！"),
    AUTH_LOGIN_TOKEN_SAVEFAIL(false, 23008, "存储令牌失败！"),
    AUTH_CHECKED_FAIL(false, 23010, "验证码发送失败，请检查电话号码是否正确！"),
    AUTH_ACCOUNT_EXISTS_MORE(false, 23013, "检测到系统存在多个账号，请联系管理员查看处理！"),
    AUTH_CHECKED_SUCCESS(true, 23011, "验证码发送成功！"),
    AUTH_CHECK_NUM_SUCCESS(true, 23014, "验证码校验成功！"),
    AUTH_CHECK_NUM_FAIL(false, 23015, "验证码错误！"),
    AUTH_SAVE_DEVICE_ID_FAIL(false, 23012, "保存设备验ID失败！");

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
        private val CACHE: ImmutableMap<Int, AuthCode>

        init {
            val builder = ImmutableMap.builder<Int, AuthCode>()
            for (commonCode in values()) {
                builder.put(commonCode.code(), commonCode)
            }
            CACHE = builder.build()
        }
    }
}
