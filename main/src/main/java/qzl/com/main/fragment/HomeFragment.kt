package qzl.com.main.fragment

import qzl.com.basecommon.base.BasListFragment
import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.basecommon.net.base.BasListPresenter
import qzl.com.main.adapter.HomeAdapter
import qzl.com.model.test.Data
import qzl.com.model.test.HomeBean
import qzl.com.main.mvp.presenter.impl.HomePresenterImpl
import qzl.com.main.mvp.widget.HomeItemView

/**
 * ClassName:HomeFragment
 * Description:
 */
class HomeFragment : BasListFragment<qzl.com.model.test.HomeBean, qzl.com.model.test.Data, HomeItemView>(){
    override fun getSpecAdapter(): BasListAdapter<qzl.com.model.test.Data, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecPresenter(): BasListPresenter {
        return HomePresenterImpl(context,this)
    }

    override fun getList(response: qzl.com.model.test.HomeBean?): List<qzl.com.model.test.Data>? {
        return response?.result?.data
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //解绑view
        presenter.destroyView()
    }
}