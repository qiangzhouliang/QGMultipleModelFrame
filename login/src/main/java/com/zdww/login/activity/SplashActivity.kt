package com.zdww.login.activity

import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.zdww.login.R
import com.zdww.login.adapter.NavigationPageAdapter
import com.zdww.login.presenter.AutoLoginPresenterImpl
import kotlinx.android.synthetic.main.p_splach.*
import kr.co.namee.permissiongen.PermissionFail
import kr.co.namee.permissiongen.PermissionGen
import kr.co.namee.permissiongen.PermissionSuccess
import org.jetbrains.anko.imageBitmap
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.common.CheckVersion
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.SysAccount
import qzl.com.basecommon.net.base.BaseView
import qzl.com.model.login.LoginModel
import qzl.com.tools.img.ImageUtils.decodeDrawableBitmap
import qzl.com.tools.operate.CompleteQuit
import qzl.com.tools.thread.ThreadPoolProxyFactory
import qzl.com.tools.utils.DeviceUtils
import qzl.com.tools.utils.StringHelper
import utilclass.PrefUtils
import utilclass.Tt
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 引导页
 * @email 2538096489@qq.com
 * @time 2019/10/30 14:28
 */
class SplashActivity: BaseActivity(), BaseView<LoginModel> {
    private var count = 4
    private var isTiming = false
    private var runnable: Runnable? = null
    private val handler = Handler()
    private var cv: CheckVersion? = null
    private var finishCheckVersion: Boolean = false
    //是否展示ViewPager
    private var finishViewPager = true

    private val picArr = intArrayOf(R.drawable.guide1, R.drawable.guide2)
    //存储指示器
    private var inds: List<ImageView>? = null
    /**
     * 最后一个页面
     */
    private var frameLayout: FrameLayout? = null
    private val viewList = ArrayList<View>()
    val presenter by lazy { AutoLoginPresenterImpl(this, this) }
    override fun getLayoutId(): Int { return R.layout.p_splach }

    override fun initData() {
        if (PrefUtils.getInt(this, Constant.LOGIN_COUNT, 0) >= 3){
            ind_layout.visibility = View.GONE
            finishViewPager = true;
            Handler().postDelayed({ checkVesion() }, 1000)
        }else{
            ind_layout.visibility = View.VISIBLE
            inds = object : ArrayList<ImageView>() {
                init {
                    add(ind_1)
                    add(ind_2)
                }
            }
            checkVesion()
            showViewPager()
        }
        requestPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }
    //获取到权限后
    @PermissionSuccess(requestCode = REQUEST_PERMISSION_CODE)
    fun doSomething() {
        count = 4//检查有没有其他设备登录
        //登陆缓存信息初始化，如果自动登陆并且没有新版本跳转河长制移动平台
        when {
            isTiming -> {
                handler.removeCallbacksAndMessages(null)
                isTiming = false
            }
        }
        when {
            finishCheckVersion && finishViewPager -> {
                finishCheckVersion = false
                finishViewPager = false
                val versionState = Integer.parseInt(StringHelper.toString(Constant.VERSION_MAP[Constant.VERSION_STATE]))
                //登陆缓存信息初始化，如果自动登陆并且没有新版本跳转河长制移动平台
//                val cacheMap = LoginCache.getCacheAll(this)
                val userInfo = SysAccount.getUserInfo(this)
                when {
                    userInfo != null -> {
                        when {
                            PrefUtils.getBoolean(this,Constant.isAutoLogin,false) && versionState == 0 -> {
                                //检查有没有其他设备登录
//                                LoadDataAsync(this@UserGuideActivity, loadDataSetting, false).execute()
                                val map = HashMap<String, String?>()
                                map["user"] = SysAccount.getUserInfo(this)?.loginAccount?:""
                                val phoneLoginFlag = PrefUtils.getString(this, Constant.isPhoneLogin, "0")
                                map["phoneLoginFlag"] = phoneLoginFlag
                                map["userMd5"] = when (phoneLoginFlag) {
                                    "1" ->  SysAccount.getUserInfo(this)?.userTele?:""
                                    else -> SysAccount.getUserInfo(this)?.loginPassword?:""
                                }
                                map["deviceId"] = DeviceUtils.getUniqueId(applicationContext)
                                map["loginType"] = getString(R.string.android_login)
                                presenter.loadDatas(map)
                                //记录登陆次数
                                var count = PrefUtils.getInt(this, Constant.LOGIN_COUNT, 0)
                                PrefUtils.setInt(this, Constant.LOGIN_COUNT, ++count)
                                //跳转到首页
                                Tt.showShort("跳转到首页")
//                                startActivityArouter(ARouterPath.homeActivity)
                                return
                            }
                            else -> goToLogin()
                        }
                    }
                    else -> goToLogin()
                }
            }
        }
    }

    // 没有获取到权限
    @PermissionFail(requestCode = REQUEST_PERMISSION_CODE)
    fun doFailSomething() {
        Tt.showShort("提示：要给权限才能运行哦!")
    }
    /**
     * @desc 跳转到登录页面
     * @author 强周亮(Qzl)
     * @time 2018-09-21 15:52
     */
    private fun goToLogin() {
        startActivityAndFFinish<LoginActivity>()
        finishWithAnimation()
    }
    companion object {
        private const val REQUEST_PERMISSION_CODE = 100
        private val permissions = arrayListOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { // 前台服务权限
            permissions.add(Manifest.permission.FOREGROUND_SERVICE)
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) { // 后台定位权限
            permissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }*/
        PermissionGen.needPermission(this, REQUEST_PERMISSION_CODE,permissions.toTypedArray() )
    }
    /**
     * @desc 检查新版本
     * @author 强周亮(Qzl)
     * @time 2018-09-27 09:29
     */
    private fun checkVesion() {
        finishCheckVersion = false
        cv = CheckVersion(this)
        cv?.setCheckVersionHandle(object : CheckVersion.CheckVersionHandle {
            override fun resultHandle() {
                finishCheckVersion = true
                requestPermission()
            }
        })
        cv?.let {
            ThreadPoolProxyFactory.normalThreadPoolProxy?.execute(it)
        }
    }

    //显示导航图片页面
    private fun showViewPager() {
        finishViewPager = false
        var bitmap: Bitmap
        var iv: ImageView?
        for (i in picArr.indices) {
            bitmap = decodeDrawableBitmap(resources,windowManager,picArr[i])
            when (i) {
                picArr.size - 1 -> {
                    when (frameLayout) {
                        null -> frameLayout = layoutInflater.inflate(R.layout.viewpage_end, null) as FrameLayout
                    }
                    iv = frameLayout?.findViewById<ImageView>(R.id.iv)
                    iv?.scaleType = ImageView.ScaleType.FIT_XY
                    iv?.imageBitmap = bitmap
                    frameLayout?.tag = bitmap
                    viewList.add(frameLayout as FrameLayout)
                }
                else -> {
                    iv = ImageView(this)
                    iv.scaleType = ImageView.ScaleType.FIT_XY
                    iv.setImageBitmap(bitmap)
                    iv.tag = bitmap
                    viewList.add(iv)
                }
            }
        }
        guidePagers.adapter = NavigationPageAdapter(picArr,viewList)
        guidePagers.setOnPageChangeListener(NavigationPageChangeListener())
        guidePagers.setOnLastPageListener {
            finishViewPager = true
            requestPermission()
        }
    }

    // viewpager的监听器，主要是onPageSelected要实现
    internal inner class NavigationPageChangeListener : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(arg0: Int) {}

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageSelected(position: Int) {
            setInds(position)
            //是否是最后一页
            guidePagers.setLastPageTag(position == picArr.size - 1)
            //如果是功能介绍就不用显示倒计时
            //如果是最后一个页面并且不是功能介绍就开始倒计时
            when {
                position == picArr.size - 1 -> {
                    count = 4
                    isTiming = true
                    val tv = frameLayout?.getChildAt(1) as TextView
                    tv.visibility = View.VISIBLE
                    tv.text = "滑动跳过  $count"
                    when (runnable) {
                        null -> runnable = object : Runnable {
                            override fun run() {
                                when {
                                    position == picArr.size - 1 && isTiming -> if (count > 1) {
                                        tv.text = "滑动跳过  " + --count
                                        handler.postDelayed(this, 1000)
                                    } else {
                                        finishViewPager = true
                                        requestPermission()
                                    }
                                }
                            }
                        }
                    }
                    handler.postDelayed(runnable, 1000)
                }
                position != picArr.size - 1 -> {
                    count = 4
                    when {
                        isTiming -> {
                            handler.removeCallbacksAndMessages(null)
                            isTiming = false
                        }
                    }
                }
            }
        }
    }

    /**
     * @desc 改变显示器的样式
     * @author Qzl
     * @time 2018-06-15 16:04
     */
    fun setInds(position: Int) {
        for (i in inds?.indices!!) {
            when (i) {
                position -> inds!![i].setImageResource(R.drawable.gray_radius)
                else -> inds!![i].setImageResource(R.drawable.white_radius)
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return when {
            keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN -> {
                finishCheckVersion = false
                finishViewPager = false
                CompleteQuit.getInstance()!!.exitAll(true)
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        var bitmap: Bitmap? = null
        var drawable: Drawable
        for (i in viewList.indices) {
            when {
                viewList[i] is ImageView -> {
                    drawable = (viewList[i] as ImageView).drawable
                    bitmap = (drawable as BitmapDrawable).bitmap
                }
                viewList[i] is FrameLayout -> bitmap = frameLayout?.tag as Bitmap
            }
            bitmap?.recycle()
            bitmap = null
        }
    }
    override fun onError(message: String?) {
        Tt.showShort("登录信息有误，请重新登录")
        goToLogin()
    }

    override fun loadSuccess(result: LoginModel?) {
        if (result?.success == true){
            finishWithAnimation()
        } else {
            //不是因为设备id保存出错的，跳到登录页
            if (result?.code != 23012) {
                goToLogin()
                Tt.showShort(result?.message)
            }
        }
    }
}
