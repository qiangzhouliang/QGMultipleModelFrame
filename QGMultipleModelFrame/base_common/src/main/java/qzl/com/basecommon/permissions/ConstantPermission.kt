package qzl.com.basecommon.permissions

import android.Manifest

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 使用权限说明常量
 * @email 2538096489@qq.com
 * @time 2020/4/15 15:33
 * @class ConstantPermission
 * @package com.gsww.hzz.tools.common
 */
object ConstantPermission {
    //摄像头权限
    const val camerContent = "在使用\"%s\"功能前，我们先要获取\"摄像头权限和写入内存卡\"权限，如果没有这两项权限，则无法使用\"%s\"功能！"
    val camerPermiss = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)

    //读内存卡的权限
    const val readSdContent = "在使用\"%s\"功能前，我们先要获取\"读取内存卡\"权限，如果没有这个项权限，则无法使用\"%s\"功能！"
    val readSdPermiss = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    //定位权限
    const val getLocationContent = "在使用\"%s\"功能前，我们先要获取\"定位\"权限来获取您当前位置，%s"
    val getLocationPermiss = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

    //写内存卡的权限
    const val writeSdContent = "在使用\"%s\"文件功能前，我们先要获取\"写入内存卡\"权限，如果没有这个项权限，则无法使用\"%s\"文件功能！"
    val writeSdPermiss = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
}