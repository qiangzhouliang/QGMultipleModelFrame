package qzl.com.qgmultiplemodelframe

import android.view.View
import qzl.com.basecommon.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        this.initHead(R.id.head_layout,"首页", View.OnClickListener { finishWithAnimation()})
    }
}
