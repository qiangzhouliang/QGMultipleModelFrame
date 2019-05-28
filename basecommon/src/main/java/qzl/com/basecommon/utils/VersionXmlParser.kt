package qzl.com.basecommon.utils

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import qzl.com.basecommon.net.domain.VersionInfo

import java.io.InputStream

/**
 * @author 强周亮(Qzl)
 * @desc 版本xml配置文件分析器
 * @email 2538096489@qq.com
 * @time 2019-05-28 16:03
 */
object VersionXmlParser {
    @Throws(Exception::class)
    fun getUpdataInfo(`is`: InputStream?): VersionInfo {
        val parser = Xml.newPullParser()
        parser.setInput(`is`, "utf-8")
        var type = parser.eventType
        val info = VersionInfo()
        while (type != XmlPullParser.END_DOCUMENT) {
            when (type) {
                XmlPullParser.START_TAG -> if ("version" == parser.name) {
                    info.version = parser.nextText()
                } else if ("isForceUpdate" == parser.name) {
                    info.isForceUpdate = java.lang.Boolean.parseBoolean(parser.nextText())
                } else if ("apkSize" == parser.name) {
                    info.apkSize = parser.nextText()
                } else if ("description" == parser.name) {
                    info.description = parser.nextText()
                }
                else -> {
                }
            }
            type = parser.next()
        }
        return info
    }
}
