package qzl.com.main.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.study.fileselectlibrary.LocalFileActivity
import com.study.fileselectlibrary.bean.FileItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.base.BaseLargeImgActivity
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.arouter.ARouterUtil
import qzl.com.basecommon.permissions.ConstantPermission
import qzl.com.basecommon.permissions.RequestPermissionUtil
import qzl.com.basecommon.permissions.RequestPermissionUtil.requestPermission
import qzl.com.main.R
import qzl.com.tools.operate.ReadProperties
import utilclass.Tt
import java.util.ArrayList

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
        select_file.setOnClickListener(this)
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
            /*仿qq文件选择*/
            select_file ->{
                startActivityForResult<LocalFileActivity>(100,"max" to 4,
                    "file" to  ArrayList<FileItem>()
                )
            }
            //动态权限申请
            permission ->{
                requestPermission(this, ConstantPermission.getLocationPermiss,
                    java.lang.String.format(ConstantPermission.getLocationContent, "我要巡河", "以便在地图中描绘巡河路径！"),
                    object : RequestPermissionUtil.PermissionListener {
                        override fun cancel(code: Int, perms: MutableList<String>) {
                            Tt.showShort("拒绝")
                        }

                        override fun success(code: Int, perms: MutableList<String>?) {
                            Tt.showShort("通过")
                        }
                    })
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == 200 && data != null) { //文件
            val resultFileList: ArrayList<FileItem> = data.getParcelableArrayListExtra("file")
            val filePath = arrayListOf<String>()
            resultFileList.forEach {
                filePath.add(it.path)
            }
            Tt.showShort(filePath.toArray().toString())
        }
    }
}
