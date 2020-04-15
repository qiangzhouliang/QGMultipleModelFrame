package qzl.com.basecommon.permissions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import qzl.com.basecommon.ui.kotlin.DialogPanel
import qzl.com.tools.utils.AppUtil.getAppDetailSettingIntent

/**
 * @desc 授权自定义提交
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/4/14 21:45
 * @class hzz
 * @package com.gsww.hzz.uikit.permissions
 */
object RequestPermissionUtil  {
    var pListener: PermissionListener? = null
    var activity:Activity? = null
    var permission: Array<String>? = null
    //请求code
    @JvmField
    var requestCode = 65533
    @JvmStatic
    @SuppressLint("RestrictedApi")
    fun requestPermission(context: Activity, args: Array<String>, content:String,listener: PermissionListener) {
        pListener = listener
        activity = context
        permission = args
        when {
            EasyPermissions.hasPermissions(context, *args) -> {
                pListener?.success(requestCode, args.toMutableList())
            }
            else -> {
                DialogPanel.dialogOperate(context, content, "提示",
                    "授权", DialogInterface.OnClickListener { dialog, which ->
                        val build = PermissionRequest.Builder(context, requestCode, *args).build()
                        build.helper.directRequestPermissions(requestCode, *args)
                        dialog.cancel()
                    },
                    "拒绝",
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        pListener?.cancel(requestCode,args.toMutableList())
                    }
                )
            }
        }
    }

    interface PermissionListener{
        //成功
        fun success(code:Int, perms: MutableList<String>?)
        // 取消
        fun cancel(code:Int, perms: MutableList<String>)
    }
    class PermissionListenerUtil{
        //成功
        fun success(code:Int, perms: MutableList<String>?){
            if (perms == null){
                when {
                    EasyPermissions.hasPermissions(activity!!, *permission!!) -> {
                        pListener?.success(requestCode, permission?.toMutableList())
                    }
                }
            } else {
                pListener?.success(code, perms)
            }
        }
        // 取消
        fun cancel(isShowSetting:Boolean,code:Int, perms: MutableList<String>){
            if (isShowSetting){
                getAppDetailSettingIntent(activity, requestCode)
            } else {
                pListener?.cancel(code, perms)
            }
        }
    }
}