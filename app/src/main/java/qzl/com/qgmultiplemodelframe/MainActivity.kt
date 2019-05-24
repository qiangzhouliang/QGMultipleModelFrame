package qzl.com.qgmultiplemodelframe

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.base.BaseLargeImgActivity
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
        load_large_image.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            //视频播放
            R.id.video_player ->{
                ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
                .withString("videoTitle", "首页跳转")
                .navigation()
            }
            //加载大图
            R.id.load_large_image ->{
                val intentImg = Intent()
                intentImg.setClass(this,BaseLargeImgActivity::class.java)
                intentImg.putExtra("imgUrl","https://img.gsdlcn.com/uploads/allimg/190120/1-1Z120161S9.jpg")
                startActivity(intentImg)
            }
        }
    }
}
