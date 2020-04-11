package qzl.com.basecommon.common

import android.app.Activity
import android.os.Handler
import android.os.Message

import java.lang.ref.WeakReference
/**
 * @author 强周亮
 * @desc handler消息弱引用 handler正确的使用方式（弱引用）
 * @email 2538096489@qq.com
 * @time 2018-09-18 11:49
 */
class MyHandler(activity: Activity) : Handler() {
    private var weakReference: WeakReference<Activity> = WeakReference(activity)
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val activity = weakReference.get()
        activity?.let {
            MyHandlerIntfUtil.doHandleMessage(msg)
        }
    }
    /**
     * @desc  消息传送接口
     * @author 强周亮（Qzl）
     * @email 2538096489@qq.com
     * @time 2018-09-18 11:54
     */
    interface MyHandlerIntf {
        fun setHandleMessage(msg: Message)
    }
    /**
     * @desc  消息传送接口工具类
     * @author 强周亮（Qzl）
     * @email 2538096489@qq.com
     * @time 2018-09-18 11:55
     */
    object MyHandlerIntfUtil {
        private var mMyHandlerIntf: MyHandlerIntf? = null

        fun setMyHandlerIntf(myHandlerIntf: MyHandlerIntf) {
            mMyHandlerIntf = myHandlerIntf
        }

        /**
         * @desc 执行message方法
         * @author 强周亮(Qzl)
         * @time 2018-09-18 13:34
         * @param msg 需要传递的message
         */
        fun doHandleMessage(msg: Message) {
            mMyHandlerIntf?.let {
                it.setHandleMessage(msg)
            }
        }
    }
}
