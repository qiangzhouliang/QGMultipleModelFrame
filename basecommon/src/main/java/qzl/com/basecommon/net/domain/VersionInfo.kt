package qzl.com.basecommon.net.domain

import java.io.Serializable

/**
 * 版本信息
 */
class VersionInfo : Serializable {
    var version: String? = null
    var isForceUpdate: Boolean = false
    var apkSize: String? = null
    var description: String? = null
}
