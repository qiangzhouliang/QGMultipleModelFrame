package qzl.com.basecommon.common

import qzl.com.tools.operate.java.ReadProperties
import java.util.*

/**
 * Created by 魏荣 on 2015/05/14.
 */
object Constant {
    //本地缓存数据存放目录
    val CACHE_DIR = "hzz"
    var baseUrl = ReadProperties.getPropertyByStr("server.url")
    var pageSize = ReadProperties.getPropertyByInt("list.pagesize")   //分页大小
    /**
     * pc端apk下载地址
     */
    var pcUpdateApkUrl = ReadProperties.getPropertyByStr("pcupdateapk.url")
    var versionInfoFlag = ReadProperties.getPropertyByStr("version.info.config")
    var versiondownloadFlag = ReadProperties.getPropertyByStr("version.download.config")

    /**
     * 参数加密字符串
     */
    var PKEY = "Ab0hU6KRzycTBiOPfa4Hgxmq1lSj8LJt"
    //版本信息缓存map
    val VERSION_MAP = HashMap<String, Any?>()
    //版本信息缓存map中key:版本状态
    @JvmStatic
    val VERSION_STATE = "state"
    //版本信息缓存map中key:版本信息
    @JvmStatic
    val VERSION_INFO = "info"
}
