package qzl.com.main.fragment

import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.basecommon.net.base.BasListPresenter
import qzl.com.basecommon.base.BasListFragment
import qzl.com.main.adapter.HomeAdapter
import qzl.com.model.test.Data
import qzl.com.model.test.HomeBean
import qzl.com.main.mvp.presenter.impl.HomePresenterImpl
import qzl.com.main.mvp.widget.HomeItemView
import qzl.com.model.common.PagePara

/**
 * ClassName:HomeFragment
 * Description:
 */
class HomeFragment : BasListFragment<HomeBean, Data, HomeItemView>(){
    override fun getSpecAdapter(): BasListAdapter<Data, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecPresenter(): BasListPresenter {
        return HomePresenterImpl(context,this)
    }

    override fun getList(response: HomeBean?): Pair<List<Data>?, PagePara?> {
        return Pair(response?.result?.data,PagePara(0,30,30,3))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //解绑view
        presenter.destroyView()
    }
}