package qzl.com.tools.utils

import android.util.Log
import qzl.com.tools.operate.java.ReadProperties

/**
 * @author 强周亮(Qzl)
 * @desc 打印日志工具类
 * @email 2538096489@qq.com
 * @time 2018-12-11 18:10
 * @class LogUtils
 */
object LogUtils {
    val LEVEL_NONE = 0
    val LEVEL_ERROR = 1
    val LEVEL_WARN = 2
    val LEVEL_INFO = 3
    val LEVEL_DEBUG = 4
    val LEVEL_VERBOSE = 5
    private val mTag = "LogUtils"
    /**
     * 显示日志控制
     */
    private val mDebuggable = ReadProperties.getPropertyByInt("logLevel")

    fun v(msg: String) {
        if (mDebuggable >= 5) {
            Log.v(mTag, msg)
        }
    }

    fun d(msg: String) {
        if (mDebuggable >= 4) {
            Log.d(mTag, msg)
        }
    }

    fun i(msg: String) {
        if (mDebuggable >= 3) {
            Log.i(mTag, msg)
        }
    }

    fun w(msg: String) {
        if (mDebuggable >= 2) {
            Log.w(mTag, msg)
        }
    }

    fun w(tr: Throwable) {
        if (mDebuggable >= 2) {
            Log.w(mTag, "", tr)
        }
    }

    fun w(msg: String?, tr: Throwable) {
        if (mDebuggable >= 2 && msg != null) {
            Log.w(mTag, msg, tr)
        }
    }

    fun e(msg: String) {
        if (mDebuggable >= 1) {
            Log.e(mTag, msg)
        }
    }

    fun e(tr: Throwable) {
        if (mDebuggable >= 1) {
            Log.e(mTag, "", tr)
        }
    }

    fun e(msg: String?, tr: Throwable) {
        if (mDebuggable >= 1 && msg != null) {
            Log.e(mTag, msg, tr)
        }
    }
}
