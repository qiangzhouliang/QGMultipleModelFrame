package com.qzl.lzshzz.common.client

/**
 * @author 强周亮
 * @desc 操作日志类型
 * @email 2538096489@qq.com
 * @time 2019/11/6 18:27
 */
object LogType {
    /**
     * 日志类型,1:登陆日志（1-1：登录成功；1-2：登陆失败），
     * 2：操作日志(2-1:新增；2-2：编辑；2-3：删除)，
     * 3：错误日志（3-1:新增；3-2：编辑；3-3：删除）
     */
    const val LOG_LOGIN_SUCCESS = "1-1"
    const val LOG_LOGIN_FAIL = "1-2"

    const val LOG_ADD_SUCCESS = "2-1"
    const val LOG_EDIT_SUCCESS = "2-2"
    const val LOG_DELETE_SUCCESS = "2-3"

    const val LOG_ADD_FAIL = "3-1"
    const val LOG_EDIT_FAIL = "3-2"
    const val LOG_DELETE_FAIL = "3-3"

}