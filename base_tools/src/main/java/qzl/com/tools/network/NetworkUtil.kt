package qzl.com.tools.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author 强周亮(Qzl)
 * @desc 网络操作相关工具类
 * @email 2538096489@qq.com
 * @time 2019-05-22 09:38
 * @class NetworkUtil
 * @package qzl.com.tools
 */
object NetworkUtil {

    val NETWORN_NONE = 0//无网络
    val NETWORN_WIFI = 1//WIFI
    val NETWORN_MOBILE = 2//3G
    val NETWORN_CONNECTED = 3//已连接

    fun getNetworkState(context: Context): Int {
        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return NETWORN_NONE

// Wifi
        var state: NetworkInfo.State = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORN_WIFI
        }

        // 3G
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORN_MOBILE
        }

        val mNetworkInfo = connManager.activeNetworkInfo
        if (mNetworkInfo != null) {
            val isConnected = mNetworkInfo.isConnected
            val available = mNetworkInfo.isAvailable
            if (isConnected) {
                return NETWORN_CONNECTED
            }
        }
        return NETWORN_NONE
    }
}
