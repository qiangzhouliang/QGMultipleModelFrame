package qzl.com.basecommon.common


import android.content.Context
import com.google.gson.Gson
import qzl.com.model.user_info.UserInfo
import utilclass.PrefUtils
import utilclass.StringHelper

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 缓存用户信息和取出用户信息
 * @email 2538096489@qq.com
 * @time 2019/11/4 15:12
 * @class SysAccount
 * @package com.zdww.basecommon.common
 */
object SysAccount {
   private var userInfo: UserInfo? = null
    //保存用户信息
    @JvmStatic
    fun setUserInfo(context: Context?, userInfo: String) {
        // 获取到的数据需要解密，jwt_token中的三部分内容是通过.来连接的，使用的时候讲中间内容部分用base64解码皆可以得到想要的值
//        println("userInfo  "+JWTUtils.decoded(jwtToken1))
        //保存一个用户信息刷新的标记位
        PrefUtils.setBoolean(context, Constant.isRefreshUserInfo, true)
        //缓存用户信息
//        PrefUtils.setString(context, Constant.CACHE_USER, JWTUtils.decoded(jwtToken1))
        PrefUtils.setString(context, Constant.CACHE_USER, userInfo)
    }

    //获取用户信息
    @JvmStatic
    fun getUserInfo(context: Context?): UserInfo? {
        when {
            userInfo == null || PrefUtils.getBoolean(context,Constant.isRefreshUserInfo,false) -> {
                val userInfos = PrefUtils.getString(context, Constant.CACHE_USER, "")
                if (StringHelper.isEmptyString(userInfos)){
                    userInfo == null
                } else {
                    try {
                        userInfo = Gson().fromJson(userInfos, UserInfo::class.java)
                        PrefUtils.setBoolean(context, Constant.isRefreshUserInfo, false)
                    } catch ( e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
        return userInfo
    }
}
