package qzl.com.basecommon.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.alibaba.android.arouter.launcher.ARouter
import org.jetbrains.anko.startActivity
import qzl.com.basecommon.R
import qzl.com.basecommon.common.SysAccount
import qzl.com.basecommon.ui.kotlin.HeadControlPanel
import qzl.com.basecommon.ui.kotlin.navigation.AppBackHandledFragment
import qzl.com.basecommon.ui.kotlin.navigation.AppBackHandledInterface
import qzl.com.model.user_info.UserInfo
import qzl.com.tools.network.NetworkReceiver
import qzl.com.tools.operate.CompleteQuit
import qzl.com.tools.utils.SerializableMap


/**
 * @author 强周亮(Qzl)
 * @desc 所有activity的基类
 * @email 2538096489@qq.com
 * @time 2019-05-22 16:25
 */
abstract class BaseActivity : AppCompatActivity(), AppBackHandledInterface {

    lateinit var headPanel: HeadControlPanel//头部显示区域
    private val networkReceiver by lazy { NetworkReceiver() }
    private var imageButton: ImageView? = null
    private var mBackHandedFragment: AppBackHandledFragment? = null
    var sysUserInfo: UserInfo? = null

    var windowScale = 0f
        internal set

    var mErrorView: View? = null//错误页面view

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        CompleteQuit.getInstance()?.pushActivity(this)
        sysUserInfo = SysAccount.getUserInfo(this)
        //去除默认的代码，可以减少页面重绘
//        window.setBackgroundDrawable(null)
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

        setContentView(getLayoutId())
        initView()
        initData()
        initListener()
        initErrorPage()
    }
    var mSwipeBackHelper: BGASwipeBackHelper? = null
    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private fun initSwipeBackFinish() {
        mSwipeBackHelper = BGASwipeBackHelper(this, object :BGASwipeBackHelper.Delegate{
            /**
             * 滑动返回执行完毕，销毁当前 Activity
             */
            override fun onSwipeBackLayoutExecuted() {
                mSwipeBackHelper?.swipeBackward();
            }
            /**
             * 正在滑动返回
             * @param slideOffset 从 0 到 1
             */
            override fun onSwipeBackLayoutSlide(slideOffset: Float) {

            }
            /**
             * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
             */
            override fun onSwipeBackLayoutCancel() {

            }

            override fun isSupportSwipeBack(): Boolean {
                return true
            }

        })
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper?.setSwipeBackEnable(true)
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper?.setIsOnlyTrackingLeftEdge(true)
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper?.setIsWeChatStyle(true)
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper?.setIsNeedShowShadow(true)
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper?.setIsShadowAlphaGradient(true)
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper?.setSwipeBackThreshold(0.3f)
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper?.setIsNavigationBarOverlap(false)
    }
    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int
    /**
     * 初始化数据
     */
    protected open fun initData() {}

    /**
     * adapter listener相关的操作
     */
    protected open fun initListener() {}
    /**
     * 初始化试图的操作
     */
    protected open fun initView() {}


    override fun onDestroy() {
        super.onDestroy()
        //结束Activity&从栈中移除该Activity
        CompleteQuit.getInstance()?.popActivity(this)
    }

    fun initHead(panelId: Int, title: String, onClickListener: View.OnClickListener) {
        headPanel = findViewById<View>(panelId) as HeadControlPanel
        headPanel.initHeadPanel()
        headPanel.setMiddleTitle(title)

        val headLayout = headPanel.headLayoutBack
        imageButton = headPanel.getmLeftImage()
        imageButton?.setImageResource(R.drawable.back)
        //setViewSize(imageButton, 20, 20);
        val scale = this.resources.displayMetrics.density
        headLayout?.setOnClickListener(onClickListener)
        windowScale = scale
    }

    fun getHeadPanel(panelId: Int): HeadControlPanel {
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
     * @desc 路由跳转页面
     * @author 强周亮
     * @time 2019/11/7 15:30
     */
    fun startActivityArouter (routPath:String,flag:Boolean = false){
        ARouter.getInstance().build(routPath).navigation(this)
        if (flag){
            finishWithAnimation()
        }
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
    /**
     * 开启activity 并且finish当前界面
     */
    inline fun <reified T:BaseActivity>startActivityAndFFinish(){
        //进入到主界面
        startActivity<T>()
        finishWithAnimation()
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
