package qzl.com.tools.operate

import android.app.Activity
import android.app.ActivityManager
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.os.Build
import java.util.*

/**
 * @author 强周亮(Qzl)
 * @desc 完全退出操作类
 * @email 2538096489@qq.com
 * @time 2019-05-22 09:44
 * @class CompleteQuit
 * @package qzl.com.tools.operate
 */
class CompleteQuit
/**
 * @desc 保证创建唯一实例
 * @author 强周亮
 * @time 2019-05-22 09:44
 */
private constructor() : Application() {

    /**
     * @desc 将activity添加到容器中
     * @author 强周亮
     * @time 2019-05-22 10:23
     */
    fun pushActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack?.add(activity)
    }

    /**
     * @desc 清空容器中的activity，保留正在运行的activit
     * @author 强周亮
     * @time 2019-05-22 10:24
     */
    fun exitAllButOne(cls: Class<*>) {
        while (true) {
            val activity = currentActivity() ?: break
            if (activity.javaClass == cls) {
                break
            }
            popActivity(activity)
        }
    }

    /**
     * @desc 清空容器的所有activity
     * @author 强周亮
     * @time 2019-05-22 10:24
     */
    fun exitAll(exit: Boolean) {
        while (true) {
            val activity = currentActivity() ?: break
            popActivity(activity)
        }
        if (exit) {
            System.exit(0)
        }
    }

    /**
     * @desc 退出栈顶
     * @author 强周亮
     * @time 2019-05-22 10:24
     */
    fun popActivity(activity: Activity?) {
        activity?.let {
            activity.finish()
            activityStack?.remove(activity)
        }
    }

    /**
     * @desc 获取当前栈顶的activity
     * @author 强周亮
     * @time 2019-05-22 10:26
     */
    fun currentActivity(): Activity? {
        var activity: Activity? = null
        activityStack?.let {
            if (it.isNotEmpty()){
                activity = it.lastElement()
            }
        }
        return activity
    }

    /**
     * @desc 退出应用
     * @author 强周亮
     * @time 2019-05-22 10:28
     */
    fun exitApp(mContent: Context) {
        AlertDialog.Builder(mContent).setTitle("提示")
            .setIcon(android.R.drawable.ic_menu_revert)
            .setMessage("您确定要退出吗？")
            .setPositiveButton("确定") { dialog, which -> quit(mContent) }.setNegativeButton("取消", null).show()
    }

    /**
     * @desc 退出App时执行的方法
     * @author 强周亮
     * @time 2019-05-22 10:28
     */
    protected fun quit(mContext: Context) {
        try {
            if (Integer.parseInt(Build.VERSION.SDK) > 7) {
                getInstance()?.exitAll(true)
            } else {
                getInstance()?.exitAll(true)
                val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                manager.restartPackage(packageName)
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

    }

    companion object {
        //创建activity容器
        private var activityStack: Stack<Activity>? = null
        //创建自定义实例
        private var instance: CompleteQuit? = null

        /**
         * @desc 通过单例模式创建实例
         * @author 强周亮
         * @time 2019-05-22 10:29
         */
        fun getInstance(): CompleteQuit? {
            if (instance == null) {
                instance = CompleteQuit()
            }
            return instance
        }
    }
}
