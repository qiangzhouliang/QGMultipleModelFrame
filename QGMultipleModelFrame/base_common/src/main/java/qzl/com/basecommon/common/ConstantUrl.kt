package qzl.com.basecommon.common

/**
 * @desc 公共地址统一配置类
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:21
 * @class lzshzz_android
 * @package com.zdww.basecommon.url
 */
object ConstantUrl {
    /* ------------登录认证 -----------------------start------------------------*/
    object Auth {
        @JvmField
        val LOGIN = "auth/userlogin"                                   // 登录地址
        // @JvmField
        val GET_APK_VERSION = "auth/getApkVersion"                     // 获取apk版本信息
        @JvmField
        val LOGOUT = "auth/userlogout"                                   // 退出地址
        @JvmField
        val AUTO_LOGIN = "auth/autoLogin"                                   // 登录地址
        @JvmField
        val GET_CHECK_NUM = "auth/getCheckNum"                         // 获取验证码
        @JvmField
        val CHECK_NUM = "auth/checkNum"                               // 校验验证码
        @JvmField
        val DELETE_REDIS = "auth/deleteRedis"                         // 删除redis信息
        @JvmField
        val GET_USER_INFO = "auth/getUserInfo"                         // 获取用户信息
        @JvmField
        val GET_YSZC = "auth/getYszc"                         // 获取隐私政策
    }

    /* ------------用户中心 -----------------------start------------------------*/
    object Ucenter {
        @JvmField
        val CHENGER_PWD = "ucenter/changePwd"                         // 修改密码
    }
    /*_____________文件管理------------------------start---------------------*/
    object File {
        //下载apk
        const val FILE_DOWNLOAD_APK = "file/downloadApk"
        const val FILE_DOWNLOAD = "file/download"
        //删除文件
        const val FILE_DELETE = "file/deleteFile"
        //上传文件
        const val FILE_UPLOAD = "file/upload"
        //展示图片
        const val FILE_SHOW = "file/show"
        //展示图片
        const val FILE_PLAY_VIDAO = "file/playVideo?fileUrl="
    }
    object App {
        //app异常信息上报
        const val APP_ERROR_REPORT = "infoManager/saveAppException"
    }

}