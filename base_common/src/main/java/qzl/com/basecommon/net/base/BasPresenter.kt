package qzl.com.basecommon.net.base

/**
 * @desc 单页面请求，没有加载更多，请求固定数据
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:20
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
interface BasPresenter {
    /**
     * 加载数据
     */
    fun loadDatas(map:HashMap<String,String?>? = null)

    /**
     * 解绑presenter和view层
     */
    fun destroyView()
}