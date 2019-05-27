package qzl.com.main.exampleactivity

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.basecommon.common.ARouterPath
import qzl.com.basecommon.net.base.BasListActivity
import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.basecommon.net.base.BasListPresenter
import qzl.com.main.R
import qzl.com.main.adapter.HomeAdapter
import qzl.com.main.mvp.presenter.impl.HomePresenterImpl
import qzl.com.main.mvp.widget.HomeItemView


class MvpActivity: BasListActivity<List<HomeItemBean>,HomeItemBean,HomeItemView>() {
    override fun initView() {
        initHead(R.id.head_layout_list,"mvp使用样例", View.OnClickListener { finishWithAnimation() })
    }
    override fun initListener() {
        super.initListener()
        adapterHome.setMyListener {
            ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
                .withString("videoTitle", it.title)
                .withString("videoUrl", it.hdUrl)
                .withString("thumbUrl", it.thumbnailPic)
                .navigation()
        }
    }
    val adapterHome by lazy { HomeAdapter() }
    override fun getSpecAdapter(): BasListAdapter<HomeItemBean, HomeItemView> {
        return adapterHome
    }

    override fun getSpecPresenter(): BasListPresenter {
        return HomePresenterImpl(this,this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }

    override fun onDestroy() {
        super.onDestroy()
        //解绑view
        presenter.destroyView()
    }
}
