package qzl.com.main.mvp.presenter.impl

import android.content.Context
import qzl.com.basecommon.net.base.BasListPresenter
import qzl.com.basecommon.net.base.BasListPresenter.Companion.TYPE_INIT_OR_REFRESH
import qzl.com.basecommon.net.base.BasListPresenter.Companion.TYPE_LOAD_MORE
import qzl.com.basecommon.net.base.BaseListView
import qzl.com.basecommon.net.base.BaseView
import qzl.com.basecommon.net.net.BaseRequest
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.main.mvp.HomeRequest
import qzl.com.model.test.HomeBean
import qzl.com.main.mvp.presenter.interf.HomePresenter
/**
 * @desc 首页业务逻辑处理类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:51
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.impl
 */
class HomePresenterImpl(var mContext:Context?,var homeView: BaseListView<HomeBean>?) : BasListPresenter,
    ResponseHandler<HomeBean> {
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
        map?.set("type", "top")
        map?.set("key", "e8159dfaee4760ddbcc13f6bb648d9e2")
        //1定义一个request+执行
        val mRequest = MRequest(mContext,
            url = "http://v.juhe.cn/toutiao/index",
            handler = this,
            type = TYPE_INIT_OR_REFRESH,
            reqMap = map,
            turnsType = HomeBean::class.java)
        BaseRequest<HomeBean>().getRequest(mRequest)
    }

    override fun loadMore(map: HashMap<String, String?>?) {
        map?.set("type", "top")
        map?.set("key", "e8159dfaee4760ddbcc13f6bb648d9e2")
        //1定义一个request+执行
        val mRequest = MRequest(mContext,
            url = "http://v.juhe.cn/toutiao/index",
            handler = this,
            type = TYPE_LOAD_MORE,
            reqMap = map,
            turnsType = HomeBean::class.java)
        BaseRequest<HomeBean>().getRequest(mRequest)
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
    override fun OnSuccess(type:Int?,result: HomeBean) {
        //区分 初始化数据和加载更多数据
        when(type){
            TYPE_INIT_OR_REFRESH -> homeView?.loadSuccess(result)
            TYPE_LOAD_MORE -> homeView?.loadMoreSuccess(result)
        }

    }
}