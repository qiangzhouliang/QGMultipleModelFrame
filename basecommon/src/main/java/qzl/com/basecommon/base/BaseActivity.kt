package qzl.com.basecommon.base

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import qzl.com.basecommon.R
import qzl.com.basecommon.ui.HeadControlPanel
import qzl.com.basecommon.ui.navigation.AppBackHandledFragment
import qzl.com.basecommon.ui.navigation.AppBackHandledInterface
import qzl.com.tools.network.NetworkReceiver
import qzl.com.tools.operate.CompleteQuit
import qzl.com.tools.utils.SerializableMap


/**
 * Created by 肖龙 on 2015/5/29.
 */
open class BaseActivity : FragmentActivity(), AppBackHandledInterface {

    lateinit var headPanel: HeadControlPanel//头部显示区域
    private val networkReceiver = NetworkReceiver()
    private var imageButton: ImageView? = null
    private var mBackHandedFragment: AppBackHandledFragment? = null

    var windowScale = 0f
        internal set

    var mErrorView: View? = null//错误页面view

    /**
     * 获取APP版本信息
     * @return
     */
    //getPackageName()是你当前类的包名，0代表是获取版本信息
    val versionName: String
        get() {
            return try {
                val packageManager = packageManager
                val packInfo = packageManager.getPackageInfo(packageName, 0)
                packInfo.versionName
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //设置沉侵式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.fitsSystemWindows = true
            window.statusBarColor = Color.TRANSPARENT
        }

        CompleteQuit.getInstance()?.pushActivity(this)
        initErrorPage()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun highApiEffects() {
        window.decorView.fitsSystemWindows = true
        //透明状态栏 @顶部
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    override fun onDestroy() {
        super.onDestroy()
        //结束Activity&从栈中移除该Activity
        CompleteQuit.getInstance()?.popActivity(this)
    }

    fun initHead(panelId: Int, title: String, onClickListener: View.OnClickListener) {
        headPanel = findViewById<View>(panelId) as HeadControlPanel
        headPanel.initHeadPanel()
        headPanel.setMiddleTitle(title)

        val headLayout = headPanel.getHeadLayoutBack()
        imageButton = headPanel.getmLeftImage()
        imageButton?.setImageResource(R.drawable.back)
        //setViewSize(imageButton, 20, 20);
        val scale = this.resources.displayMetrics.density
        headLayout.setOnClickListener(onClickListener)
        windowScale = scale
    }

    fun getHeadPanel(panelId: Int): HeadControlPanel {
        if (headPanel == null) {
            headPanel = findViewById<View>(panelId) as HeadControlPanel
        }
        return headPanel
    }

    protected fun registerNetworkReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, intentFilter)
    }

    protected fun unregisterNetWorkReceiver() {
        unregisterReceiver(networkReceiver)
    }

    fun setViewSize(view: View, width: Int, height: Int) {
        val params = view.layoutParams
        val scale = this.resources.displayMetrics.density
        params.height = (height * scale + 0.5f).toInt()
        params.width = (width * scale + 0.5f).toInt()
        view.layoutParams = params
    }

    /**
     * 保存fragment
     */
    override fun setSelectedFragment(selectedFragment: AppBackHandledFragment) {
        this.mBackHandedFragment = selectedFragment
    }

    /**
     * @return true消费 false未消费
     */
    fun prepareBack(): Boolean {
        return if (null == mBackHandedFragment) false else mBackHandedFragment!!.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            if (!prepareBack()) {
                finishWithAnimation()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //默认关闭当前activity时
    fun finishWithAnimation() {
        super.finish()
        finishRightOut()
    }

    //关闭当前activity
    fun finishRightInLeftOut() {
        super.finish()
        finishLeftOut()
    }

    //默认打开activity时
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        startRightIn()
    }

    fun startActivityLeftInRightOut(intent: Intent) {
        super.startActivity(intent)
        startleftIn()
    }

    fun startActivityRightOut(intent: Intent) {
        super.startActivity(intent)
        finishRightOut()
    }

    fun startActivityLeftOut(intent: Intent) {
        super.startActivity(intent)
        finishLeftOut()
    }

    //右进左出
    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        startRightIn()
    }

    //打开新activity关闭当前，无关闭动画
    fun startActivityAndFinish(intent: Intent) {
        startActivity(intent)
        super.finish()
    }

    //打开新activity关闭当前，左进右出
    fun startActivityAndFinishLeftInRightOut(intent: Intent) {
        startActivityLeftInRightOut(intent)
        //        finish();
    }

    fun startleftIn() {
        overridePendingTransition(R.anim.push_left_in, R.anim.default_anim)
    }

    fun startRightIn() {
        overridePendingTransition(R.anim.push_right_in, R.anim.default_anim)
    }

    fun finishLeftOut() {
        overridePendingTransition(R.anim.default_anim, R.anim.push_left_out)
    }

    fun finishRightOut() {
        overridePendingTransition(R.anim.default_anim, R.anim.push_right_out)
    }

    /**
     * @desc 显示加载失败时的自定义页面
     * @author Qzl
     * @time 2018-06-25 20:02
     */
    private fun initErrorPage() {
        if (mErrorView == null) {
            mErrorView = View.inflate(this, R.layout.layout_load_error, null)
        }
    }

    /**
     * @desc 显示自定义错误提示页面，用一个View覆盖在WebView
     * @author Qzl
     * @time 2018-06-25 20:04
     */
    fun showErrorPage(view: View) {
        if (view is RelativeLayout) {
            view.removeAllViews()
            view.addView(mErrorView) //添加自定义的错误提示的View
        } else if (view is LinearLayout) {
            view.removeAllViews()
            view.addView(mErrorView)
        }
    }

    /**
     * @desc 打开新页面，切传递map值
     * @author Qzl
     * @time 2018-07-30 18:52
     */
    fun startActivitySetMap(activity: Activity, intent: Intent, map: Map<String, Any>) {
        val bundle = Bundle()
        bundle.putSerializable("tmpMap", SerializableMap().setMap(map))
        intent.putExtras(bundle)
        activity.startActivity(intent)
    }

    /**
     * @desc 将popuwindow显示在某一控件的下面
     * @author 强周亮(Qzl)
     * @time 2018-09-07 14:23
     * @param popupWindow 要显示的popuwindow
     * @param view popuwindow要显示的目标view
     */
    fun showPopuWindowAsDrawView(activity: Activity, popupWindow: PopupWindow, view: View,setHeight:Int) {
        val mLocation = IntArray(2)
        view.getLocationInWindow(mLocation)
        popupWindow.showAtLocation(view, Gravity.TOP, 0, mLocation[1] + view.height + setHeight)
    }

    companion object {
        /**
         * 获得状态栏的高度
         * @param context
         * @return
         */
        fun getStatusHeight(context: Context): Int {
            var statusHeight = -1
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val `object` = clazz.newInstance()
                val height = Integer.parseInt(
                    clazz.getField("status_bar_height")
                        .get(`object`).toString()
                )
                statusHeight = context.resources.getDimensionPixelSize(height)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return statusHeight
        }
    }
}
