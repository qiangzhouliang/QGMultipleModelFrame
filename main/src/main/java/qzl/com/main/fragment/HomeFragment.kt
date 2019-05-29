package qzl.com.main.fragment

import com.itheima.player.model.bean.HomeItemBean
import qzl.com.basecommon.base.BasListFragment
import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.basecommon.net.base.BasListPresenter
import qzl.com.main.adapter.HomeAdapter
import qzl.com.main.mvp.presenter.impl.HomePresenterImpl
import qzl.com.main.mvp.widget.HomeItemView

/**
 * ClassName:HomeFragment
 * Description:
 */
class HomeFragment : BasListFragment<List<HomeItemBean>, HomeItemBean, HomeItemView>(){
    override fun getSpecAdapter(): BasListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecPresenter(): BasListPresenter {
        return HomePresenterImpl(context,this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //解绑view
        presenter.destroyView()
    }
}