package com.zdww.lzshzz.common.model.response

import lombok.ToString
/**
 * @author 强周亮
 * @desc 公共返回结果代码
 * @email 2538096489@qq.com
 * @time 2019/11/20 13:55
 */
@ToString
enum class CommonCode private constructor(//    private static ImmutableMap<Integer, CommonCode> codes ;
        //操作是否成功
        private var success: Boolean, //操作代码
        private var code: Int, //提示信息
        private var message: String) : ResultCode {
    SUCCESS(true, 10000, "操作成功！"),
    UNAUTHENTICATED(false, 10001, "检测到你长时间未使用，请重新登录使用！"),
    UNAUTHORISE(false, 10002, "权限不足，无权操作！"),
    INVALID_PARAM(false, 10003, "非法参数！"),
    CHECK_NUM_MORE(false, 10004, "检测到您输入错误密码的次数过多！"),
    FFATL_PHONE_ERROR(false, 10005, "发送失败，检测到该电话号码不合法！"),
    SEND_DATA_FAIL(false, 10006, "同步巡河记录数据失败！"),
    DELETE_DATA_FAIL(false, 10007, "删除省级数据失败！"),
    FAIL(false, 11111, "操作失败！"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！");

    override fun success(): Boolean {
        return success
    }

    override fun code(): Int {
        return code
    }

    override fun message(): String {
        return message
    }


}
