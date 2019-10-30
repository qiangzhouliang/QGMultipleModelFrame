package qzl.com.qgmultiplemodelframe

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import qzl.com.tools.operate.java.ReadProperties
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
        instance = this
        if (isUIProcess()) {
            //初始化弹窗控件
            Tt.init(this)
            //初始化路由
            initARouter()
            //未处理异常、运行时异常捕获
            when (ReadProperties.getPropertyByStr("isOpenException")){
                "true" -> ExceptionHandler.getInstance().init(applicationContext)
            }
        }
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
