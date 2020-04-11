package qzl.com.model.app_info

import java.io.Serializable

/**
 * 版本信息
 */
class VersionInfo : Serializable {
    var version: String? = null
    var forceUpdate: Boolean = false
    var apkSize: String? = null
    var description: String? = null
    var fileUrl: String? = null
}
