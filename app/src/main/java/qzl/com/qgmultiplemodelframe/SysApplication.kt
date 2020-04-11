package qzl.com.qgmultiplemodelframe

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import android.text.TextUtils
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.alibaba.android.arouter.launcher.ARouter
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import org.xutils.x
import qzl.com.tools.operate.ReadProperties
import qzl.com.tools.utils.AppUtil
import qzl.com.tools.utils.MyLogUtils
import utilclass.Tt
import java.util.*

/**
 * @author 强周亮
 * @desc 全局应用
 * @email 2538096489@qq.com
 * @time 2018-08-31 09:27
 * @class SysApplication
 */
class SysApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //判断签名信息是否一致，不一致则有可能被重新打包了
        /*if (AppUtil.getSignature(this) != 1608383266) {
            Tt.showShort("APP可能被重打包了，请联系系统管理员查看处理")
            try {
                Thread.sleep(1000)
                CompleteQuit.getInstance()?.quit(this)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }*/

        instance = this
        //初始化读取配置文件信息
        ReadProperties.setContext(this)
//        初始化日志控件
        MyLogUtils.init(this)
        if (isUIProcess()) {
            //初始化弹窗控件
            Tt.init(this)
            initARouter()
            initXutils()
            initErrorException()
//            友盟初始化
            initUM()

            //初始化滑动退出
            BGASwipeBackHelper.init(this, null);
        }
    }

    private fun initUM() {
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,null)
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
        if (AppUtil.isApkInDebug(this)) {
            /**
             * 设置组件化的Log开关
             * 参数: boolean 默认为false，如需查看LOG设置为true
             */
            UMConfigure.setLogEnabled(true)
        }
    }

    private fun initErrorException() {
        //未处理异常、运行时异常捕获
        if (ReadProperties.getPropertyByStr("isOpenException") == "true"){
            ExceptionHandler.getInstance().init(applicationContext)
        }
    }



    private fun initXutils() {
        x.Ext.init(this)
        x.Ext.setDebug(BuildConfig.DEBUG) // 是否输出debug日志, 开启debug会影响性能.
    }


    /**
     * 初始化路由
     */
    fun initARouter() {
        // 初始化 ARouter
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        ARouter.init(instance)// 尽可能早，推荐在Application中初始化
    }

    private fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
    /**
     * 判断是否是UI进程
     *
     * @return
     */
    fun isUIProcess(): Boolean {
        val pid = android.os.Process.myPid()
        var processName = ""
        val activityManager = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcessInfo in activityManager.runningAppProcesses) {
            if (runningAppProcessInfo.pid == pid) {
                processName = runningAppProcessInfo.processName
                break
            }
        }
        return TextUtils.equals(packageName, processName)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    companion object {
        private val activityStack: Stack<Activity>? = null
        var instance: SysApplication? = null
            private set
    }
}
