package qzl.com.fileuploadanddownload

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.common.ARouterPath

@Route(path = ARouterPath.FILE)
class FileActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_file
    }

    override fun initView() {
        this.initHead(R.id.head_layout,"文件上传和下载", View.OnClickListener { finishWithAnimation()})
    }

    override fun initListener() {
    }
    override fun onClick(v: View) {
        when(v.id){

        }
    }
}
