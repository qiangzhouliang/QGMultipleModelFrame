package qzl.com.tools.utils

import android.content.Context

/**
 * @desc 和APP的信息相关的操作类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-22 16:27
 * @class QGMultipleModelFrame
 * @package qzl.com.tools.utils
 */
object AppInfoUtil {
    /**
     * @desc 获取APP版本信息
     * context.packageName是你当前类的包名，0代表是获取版本信息
     * @author 强周亮
     * @time 2019-05-22 16:30
     */
    fun getVersionName(context: Context):String{
        return try {
            val packInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packInfo.versionName
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}