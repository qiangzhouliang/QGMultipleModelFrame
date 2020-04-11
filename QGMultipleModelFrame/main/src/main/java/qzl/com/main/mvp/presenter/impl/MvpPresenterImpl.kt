package qzl.com.main.mvp.presenter.impl

import android.content.Context
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.basecommon.net.base.BasListPresenter.Companion.TYPE_INIT_OR_REFRESH
import qzl.com.basecommon.net.base.BasListPresenter.Companion.TYPE_LOAD_MORE
import qzl.com.basecommon.net.base.BaseListView
import qzl.com.basecommon.net.base.BaseView
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.main.mvp.MvpRequest
import qzl.com.main.mvp.presenter.interf.MvpPresenter

/**
 * @desc mvp请求示例
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:51
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.impl
 */
class MvpPresenterImpl(var mContext:Context?, var homeView: BaseListView<List<HomeItemBean>>?) : MvpPresenter,
    ResponseHandler<List<HomeItemBean>> {
    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
        homeView.let {
            homeView = null
        }
    }
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas(map: HashMap<String, String?>?,isShowProgress:Boolean) {
        //1定义一个request+执行
        MvpRequest(mContext,TYPE_INIT_OR_REFRESH,0,this).execute()
    }

    override fun loadMore(map: HashMap<String, String?>?) {
        //1定义一个request
        MvpRequest(mContext,TYPE_LOAD_MORE,0,this).execute()
    }

    /**
     * 加载数据失败
     */
    override fun onError(type:Int?,msg: String?) {
        homeView?.onError(msg)
    }
    /**
     * 加载数据成功
     */
    override fun OnSuccess(type:Int?,result: List<HomeItemBean>) {
        //区分 初始化数据和加载更多数据
        when(type){
            TYPE_INIT_OR_REFRESH -> homeView?.loadSuccess(result)
            TYPE_LOAD_MORE -> homeView?.loadMoreSuccess(result)
        }
    }
}