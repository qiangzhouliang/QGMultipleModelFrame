package com.zdww.login.view

/**
 * @desc 所有单数据请求的view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:15
 */
interface DeleteRedisView<RESPONSE> {
    /**
     * 初始化数据或者刷新数据成功
     */
    fun onSuccess(req: RESPONSE?)
}