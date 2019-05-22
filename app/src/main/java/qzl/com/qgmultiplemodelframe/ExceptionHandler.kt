package qzl.com.qgmultiplemodelframe

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Looper
import qzl.com.tools.operate.CompleteQuit
import qzl.com.tools.thread.ThreadPoolProxyFactory
import qzl.com.tools.utils.LogUtils
import java.io.PrintWriter
import java.io.StringWriter

/**
 * @ClassName: ExceptionHandler
 * @Description:  捕获未处理异常和运行时异常的类
 * @author lsn
 * @date 2018/7/4 11:09
 */
class ExceptionHandler
/**
 * @desc 保证只创建一个实例
 * @author Qzl
 * @time 2018-08-31 09:20
 */
private constructor() : Thread.UncaughtExceptionHandler {
    /**
     * 应用的Context对象
     */
    private var context: Context? = null
    /**
     * 系统默认的UncaughtException处理类
     */
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null


    /**
     * @desc 初始化，注册Context对象，获取系统默认的UncaughtException处理器，设置为自定义捕获机制默认的的处理器
     * @author Qzl
     * @time 2018-08-31 09:20
     * @param context 上下文对象
     */
    fun init(context: Context) {
        this.context = context
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        e.printStackTrace(printWriter);
        val msg = writer.toString()
        LogUtils.e("\r\nCurrentThread:" + Thread.currentThread() + "\r\nException:\r\n" + msg)
        ThreadPoolProxyFactory.normalThreadPoolProxy?.execute (Runnable {
            Looper.prepare()
            try {
                val intent = Intent()
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                intent.action = "qzl.com.action.NOTIFY_ERROR"
                intent.putExtra("msg", msg)
                context?.startActivity(intent)
                CompleteQuit.getInstance()?.exitAll(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Looper.loop()
        })
    }

    companion object {
        /**
         * 创建自定义异常捕获实例
         */
        @SuppressLint("StaticFieldLeak")
        private var instance: ExceptionHandler? = null
        /**
         * @desc 通过单例模式创建自定义异常捕获实例
         * @author Qzl
         * @time 2018-08-31 09:20
         */
        fun getInstance(): ExceptionHandler {
            if (instance == null) {
                instance = ExceptionHandler()
            }
            return instance as ExceptionHandler
        }
    }
}
