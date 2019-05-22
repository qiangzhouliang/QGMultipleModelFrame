package qzl.com.qgmultiplemodelframe

import android.os.Bundle
import android.view.View
import qzl.com.basecommon.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initHead(R.id.head_layout,"首页", View.OnClickListener { finishWithAnimation()})
    }
}
