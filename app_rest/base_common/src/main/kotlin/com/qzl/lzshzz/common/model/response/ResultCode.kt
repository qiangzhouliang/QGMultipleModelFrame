package com.qzl.lzshzz.common.model.response

/**
 * 10000-- 通用错误代码
 * 22000-- 用户中心错误代码
 * 23000-- 登录认证错误代码
 * 24000-- 信息管理错误
 * 25000-- 文件管理
 * 26000-- 河道巡检
 */
interface ResultCode {
    //操作是否成功,true为成功，false操作失败
    fun success(): Boolean

    //操作代码
    fun code(): Int

    //提示信息
    fun message(): String

}
