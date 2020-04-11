package com.zdww.login.view

/**
 * @desc 校验验证码返回结果处理
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:15
 */
interface JiaoYanCheckNumView<RESPONSE> {
    /**
     * 初始化数据或者刷新数据成功
     */
    fun onJiaoYanmSuccess(list: RESPONSE?)
}