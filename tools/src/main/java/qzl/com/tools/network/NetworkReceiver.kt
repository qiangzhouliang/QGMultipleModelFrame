package qzl.com.tools.network

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import utilclass.Tt

/**
 * @author 强周亮(Qzl)
 * @desc网络广播接收
 * @email 2538096489@qq.com
 * @time 2019-05-22 09:46
 * @class
 * @package
 */
class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val mNetWorkState = NetworkUtil.getNetworkState(context)
            if (mNetWorkState == NetworkUtil.NETWORN_NONE) {
                Tt.showShort("网络连接已断开")
                val b = AlertDialog.Builder(context)
                    .setTitle("没有可用的网络")
                    .setMessage("是否对网络进行设置？")
                b.setPositiveButton("设置") { dialog, whichButton ->
                    if (android.os.Build.VERSION.SDK_INT > 10) {
                        //3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
                        context.startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
                    } else {
                        context.startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS))
                    }
                }.setNeutralButton("取消") { dialog, whichButton -> dialog.cancel() }.show()
            } else {
                Tt.showShort("网络已连接")
            }
        }
    }
}
