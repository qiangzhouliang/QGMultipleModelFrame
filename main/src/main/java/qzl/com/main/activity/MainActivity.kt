package qzl.com.main.activity

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.base.BaseLargeImgActivity
import qzl.com.basecommon.common.ARouterPath
import qzl.com.main.R

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        this.initHead(R.id.head_layout,"功能汇总", View.OnClickListener { finishWithAnimation()})
    }

    override fun initListener() {
        video_player.setOnClickListener(this)
        load_large_image.setOnClickListener(this)
        mvp.setOnClickListener(this)
        net2.setOnClickListener(this)
        fileuploadordownload.setOnClickListener(this)
        home.setOnClickListener(this)
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
                startActivity<BaseLargeImgActivity>(
                    "imgUrl" to "https://img.gsdlcn.com/uploads/allimg/190120/1-1Z120161S9.jpg"
                )
            }
            R.id.mvp ->{
                startActivity<MvpActivity>()
            }
            /*网络请求封装二*/
            R.id.net2 ->{
                startActivity<Net2Activity>()
            }
            /*文件上传和下载*/
            R.id.fileuploadordownload ->{
                ARouter.getInstance().build(ARouterPath.FILE).navigation()
            }
            /*首页*/
            R.id.home ->{
                startActivity<HomeActivity>()
            }
        }
    }
}
