package qzl.com.main.activity

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.basecommon.net.base.BasListPresenter
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.base.BasListActivity
import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.main.R
import qzl.com.main.adapter.MvpAdapter
import qzl.com.main.mvp.presenter.impl.MvpPresenterImpl
import qzl.com.main.mvp.widget.MvpItemView
import qzl.com.model.common.PagePara


class MvpActivity: BasListActivity<List<HomeItemBean>, HomeItemBean, MvpItemView>() {
    override fun initView() {
        initHead(R.id.head_layout,"mvp使用样例", View.OnClickListener { finishWithAnimation() })
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
    val adapterHome by lazy { MvpAdapter() }
    override fun getSpecAdapter(): BasListAdapter<HomeItemBean, MvpItemView> {
        return  adapterHome
    }

    override fun getSpecPresenter(): BasListPresenter {
        return MvpPresenterImpl(this,this)
    }

    override fun getList(response: List<HomeItemBean>?): Pair<List<HomeItemBean>?, PagePara?> {
        return Pair(response,null)
    }

    override fun onDestroy() {
        super.onDestroy()
        //解绑view
        presenter.destroyView()
    }
}
