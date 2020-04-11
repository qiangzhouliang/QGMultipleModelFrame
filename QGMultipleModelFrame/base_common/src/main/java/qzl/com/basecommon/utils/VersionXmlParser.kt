package qzl.com.basecommon.utils

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import qzl.com.model.app_info.VersionInfo

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
                XmlPullParser.START_TAG -> when (parser.name) {
                    "version" -> { info.version = parser.nextText() }
                    "forceUpdate" -> { info.forceUpdate = java.lang.Boolean.parseBoolean(parser.nextText()) }
                    "apkSize" -> {
                        info.apkSize = parser.nextText()
                    }
                    "description" -> {
                        info.description = parser.nextText()
                    }
                }
                else -> {
                }
            }
            type = parser.next()
        }
        return info
    }
}
