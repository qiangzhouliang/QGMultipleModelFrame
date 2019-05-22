package qzl.com.qgmultiplemodelframe

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.common.ARouterPath

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        this.initHead(R.id.head_layout,"首页", View.OnClickListener { finishWithAnimation()})
    }

    override fun initListener() {
        video_player.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            //视频播放
            R.id.video_player ->{
                ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
                .withString("videoTitle", "首页跳转")
                .navigation()
            }
        }
    }
}
