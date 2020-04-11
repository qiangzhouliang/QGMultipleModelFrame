package qzl.com.tools.utils

import android.content.Context
import android.text.TextUtils
import android.util.Log
import org.xutils.common.util.LogUtil
import qzl.com.tools.utils.AppUtil.isApkInDebug

/**
 * @author 强周亮(Qzl)
 * @desc 打印日志工具类
 * @email 2538096489@qq.com
 * @time 2018-12-11 18:10
 * @class LogUtils
 */
object MyLogUtils {
    val LEVEL_NONE = 0
    val LEVEL_ERROR = 1
    val LEVEL_WARN = 2
    val LEVEL_INFO = 3
    val LEVEL_DEBUG = 4
    val LEVEL_VERBOSE = 5
    /**
     * 显示日志控制
     */
    private var mDebuggable = 0
    //初始化日志加载控件
    fun init(context: Context){
        mDebuggable = if (isApkInDebug(context)) {
            LEVEL_VERBOSE
        } else {
            LEVEL_NONE
        }
    }
    private fun generateTag(): String? {
        val caller = Throwable().stackTrace[2]
        var tag = "%s.%s(L:%d)"
        var callerClazzName = caller.className
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
        tag = String.format(tag, callerClazzName, caller.methodName, caller.lineNumber)
        tag = if (TextUtils.isEmpty(LogUtil.customTagPrefix)) tag else LogUtil.customTagPrefix + ":" + tag
        return tag
    }
    @JvmStatic
    fun v(msg: String?) {
        if (mDebuggable >= 5) {
            val tag = generateTag()
            Log.v(tag, msg)
        }
    }
    @JvmStatic
    fun d(msg: String?) {
        if (mDebuggable >= 4) {
            val tag = generateTag()
            Log.d(tag, msg)
        }
    }
    @JvmStatic
    fun i(msg: String?) {
        if (mDebuggable >= 3) {
            val tag = generateTag()
            Log.i(tag, msg)
        }
    }
    @JvmStatic
    fun w(msg: String?) {
        if (mDebuggable >= 2) {
            val tag = generateTag()
            Log.w(tag, msg)
        }
    }
    @JvmStatic
    fun w(tr: Throwable) {
        if (mDebuggable >= 2) {
            val tag = generateTag()
            Log.w(tag, "", tr)
        }
    }
    @JvmStatic
    fun w(msg: String?, tr: Throwable) {
        if (mDebuggable >= 2 && msg != null) {
            val tag = generateTag()
            Log.w(tag, msg, tr)
        }
    }

    @JvmStatic
    fun e(msg: String?) {
        if (mDebuggable >= 1) {
            val tag = generateTag()
            Log.e(tag, msg?:"")
        }
    }
    @JvmStatic
    fun e(tr: Throwable) {
        if (mDebuggable >= 1) {
            val tag = generateTag()
            Log.e(tag, "", tr)
        }
    }
    @JvmStatic
    fun e(msg: String?, tr: Throwable) {
        if (mDebuggable >= 1 && msg != null) {
            val tag = generateTag()
            Log.e(tag, msg, tr)
        }
    }
}
