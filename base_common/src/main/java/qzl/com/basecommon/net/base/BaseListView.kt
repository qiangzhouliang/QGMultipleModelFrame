package qzl.com.basecommon.net.base

/**
 * @desc 所有下拉刷新和上拉加载更多列表界面的view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:15
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
interface BaseListView<RESPONSE> :BaseView<RESPONSE>{
    /**
     * 加载更多成功
     */
    fun loadMoreSuccess(list: RESPONSE?)
}