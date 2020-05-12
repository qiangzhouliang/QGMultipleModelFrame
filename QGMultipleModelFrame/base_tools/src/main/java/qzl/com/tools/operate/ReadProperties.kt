package qzl.com.tools.operate

import android.content.Context
import com.qzl.toast.MyToast
import qzl.com.tools.utils.AppUtil.isApkInDebug
import java.util.*

object ReadProperties {
    private var defaultProperty: Properties? = null
    fun setContext(context: Context?) {
        if (context == null) {
            MyToast.showLong("请先初始化配置文件读取类！！！")
            return
        }
        if (isApkInDebug(context)) {
            init("debug")
        } else {
            init("release")
        }
    }

    /**
     * @desc 配置参数的名称
     * @author 强周亮
     * @time 2019-01-24 11:51
     */
    fun init(str: String) {
        if (defaultProperty == null) {
            defaultProperty = Properties()
        }
        val stream =
            ReadProperties::class.java.getResourceAsStream("/assets/$str.properties")
        try {
            defaultProperty!!.load(stream)
        } catch (e: Exception) {
        }
        defaultProperty!!.putAll(System.getProperties())
    }

    /**
     * @param propertyName :对象名称
     * @return 对象值
     * 取得配置文件的对象值
     */
    fun getPropertyByStr(propertyName: String): String {
        return defaultProperty!![propertyName].toString()
    }

    /**
     * @param propertyName :对象名称
     * @return 对象值
     * 取得配置文件的对象值
     */
    fun getPropertyByInt(propertyName: String): Int {
        return getPropertyByStr(propertyName).toInt()
    }
}