package qzl.com.main

import android.Manifest
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import kotlinx.android.synthetic.main.p_splach.*
import kr.co.namee.permissiongen.PermissionFail
import kr.co.namee.permissiongen.PermissionGen
import kr.co.namee.permissiongen.PermissionSuccess
import qzl.com.basecommon.base.BaseActivity
import utilclass.Tt


class SplashActivity: BaseActivity(),ViewPropertyAnimatorListener {

    override fun getLayoutId(): Int {
        return R.layout.p_splach
    }

    override fun initData() {
        PermissionGen.needPermission(this, 100, arrayOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }
    @PermissionSuccess(requestCode = 100)
    fun doSomething() {
        //缩小动画
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2000
    }
    @PermissionFail(requestCode = 100)
    fun doFailSomething() {
        Tt.showShort("要给权限才能运行了")
    }

    override fun onAnimationEnd(p0: View?) {
        //进入到主界面
        startActivityAndFFinish<MainActivity>()
    }

    override fun onAnimationCancel(p0: View?) {
    }

    override fun onAnimationStart(p0: View?) {
    }
}
