package qzl.com.main.activity

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.base.BaseLargeImgActivity
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.arouter.ARouterUtil
import qzl.com.main.R
import qzl.com.tools.operate.ReadProperties
import utilclass.Tt

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        this.initHead(R.id.head_layout,"功能汇总", View.OnClickListener { finishWithAnimation()})
    }

    override fun initListener() {
        video_player.setOnClickListener(this)
        load_large_image.setOnClickListener(this)
        mvp.setOnClickListener(this)
        login.setOnClickListener(this)
        fileuploadordownload.setOnClickListener(this)
        home.setOnClickListener(this)
        chart.setOnClickListener(this)
        Tt.showShort(ReadProperties.getPropertyByStr("server.url"))
    }
    override fun onClick(v: View) {
        when(v){
            //视频播放
            video_player ->{
                ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
                    .withString("videoTitle", "首页跳转")
                    .navigation()
            }
            //加载大图
            load_large_image ->{
                startActivity<BaseLargeImgActivity>(
                    "imgUrl" to "https://img.gsdlcn.com/uploads/allimg/190120/1-1Z120161S9.jpg"
                )
            }
            mvp ->{
                startActivity<MvpActivity>()
            }
            /*登录页*/
            login ->{
                ARouterUtil.arouterToAct(this,ARouterPath.Login.LOGIN)
            }
            /*文件上传和下载*/
            fileuploadordownload ->{
                ARouterUtil.arouterToAct(this,ARouterPath.FILE)
            }
            /*首页*/
            home ->{
                startActivity<HomeActivity>()
            }
            /*统计图表*/
            chart ->{
                ARouterUtil.arouterToAct(this,ARouterPath.CHART)
            }
        }
    }
}
