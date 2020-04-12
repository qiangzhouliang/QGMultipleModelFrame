package qzl.com.basecommon.common

import qzl.com.tools.operate.ReadProperties
import java.util.*

/**
 * Created by 魏荣 on 2015/05/14.
 */
object Constant {
    //本地缓存数据存放目录
    val CACHE_DIR = "hzz"
    var baseUrl = ReadProperties.getPropertyByStr("server.url")
    var pageSize = ReadProperties.getPropertyByInt("list.pagesize")   //分页大小
    var versionInfoFlag = ReadProperties.getPropertyByStr("version.info.config")
    var versiondownloadFlag = ReadProperties.getPropertyByStr("version.download.config")

    //获取显示图片 press为1 则为压缩图片
    @JvmStatic
    fun getShowImgPress(fileName:String,press: String = "1"):String{
        return baseUrl+ConstantUrl.File.FILE_SHOW+"?press=${press}&fileName=${fileName}"
    }
    /**
     * 参数加密字符串
     */
    @JvmField
    var PKEY = "Ab0hU6KRzycTBiOPfa4Hgxmq1lSj8LJt"
    //有弹框时的背景透明度设置
    @JvmField
    var SCREEM_BACKGROUND_ALPHA = 0.7f
    //版本信息缓存map
    @JvmField
    val VERSION_MAP = HashMap<String, Any?>()
    //版本信息缓存map中key:版本状态
    @JvmField
    val VERSION_STATE = "state"
    //版本信息缓存map中key:版本信息
    @JvmField
    val VERSION_INFO = "info"
    //缓存用户信息
    @JvmField
    val CACHE_USER = "userInfo"
    //刷新用户信息标记 true 刷新 false 没有刷新
    @JvmField
    val isRefreshUserInfo = "isRefreshUserInfo"
    @JvmField
    val TOKEN = "Token"  //缓存token的key
    @JvmField
    val jwtToken = "jwtToken"  //缓存jwt令牌
    @JvmField
    val refreshToken = "refreshToken"  //缓存刷新令牌
    @JvmField
    val isAutoLogin = "isAutoLogin"  //是否记住密码
    @JvmField
    val isPhoneLogin = "isPhoneLogin"  //账号密码登录标识

    /**
     * tabLayout滑动时，是否需要重新刷新
     */
    const val IS_TAB_CONTENT = "isTabContent"

    //新密码长度6-20，必须包含数字、字母、特殊字符！
    @JvmField
    val REGULAR_PASSWORD = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#\$%^&*()_+`\\-={}:\";'<>?,.\\/]).{6,20}\$"

    //缓存登录用户名、用户Id、单位名称。
    @JvmField
    val LOGIN_COUNT = "loginCount"
}
