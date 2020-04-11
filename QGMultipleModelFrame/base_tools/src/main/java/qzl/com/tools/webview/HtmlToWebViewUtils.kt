package qzl.com.tools.webview

import java.util.regex.Pattern

/**
 * @author Qzl
 * @desc 构建webView需要的HTML页面的代码
 * @email 2538096489@qq.com
 * @time 2019-03-22 13:36
 */
object HtmlToWebViewUtils {
    fun getStrFormatHtml(content: String): String {
        var content = content
        val contentStyle = ("<style type=" + "text/css" + ">"
                + " " + "table" + "{" + " "
                + "border-collapse:collapse;" + "}" + " " +

                "  th, td" + "{" + " "
                + "  border: 2px solid ;" + "}" + " "
                + "*{ word-break: break-all; }" + " "
                + "img" + "{float: left;" +
                " width:99% }" + " "
                + " </style>")
        content = content.replace("style=\"line-height: 0px;\"".toRegex(), "style=\"line-height: 20px;\"")
        content = content.replace("style'line-height: 0px;'".toRegex(), "style='line-height: 20px;'")
        content = content.replace("line-height: 0px;".toRegex(), "line-height: 20px;")
        content = content.replace("height:\\s+\\d{2,}px;".toRegex(), "height: auto;")
        content = content.replace("TEXT-INDENT:\\s+\\d{2,}px".toRegex(), "TEXT-INDENT: 20px")
        content = content.replace("text-indent:\\s+\\d{2,}px".toRegex(), "text-indent: 20px")
        content = replaceHtmlTag(content, "img", "width")
        content = contentStyle + content
        return content
    }

    fun replaceHtmlTag(str: String, beforeTag: String, tagAttrib: String): String {
        val regxpForTag = "<\\s*$beforeTag\\s+([^>]*)\\s*"//<\s*img\s+([^>]*)\s*   匹配img标签
        val regxpForTagAttrib = "$tagAttrib=\\s*\"([^\"]+)\""//width=//s*"([^"]+)" 匹配width=
        //Pattern.CASE_INSENSITIVE 忽略大小写
        val patternForTag =
            Pattern.compile(regxpForTag, Pattern.CASE_INSENSITIVE)//<//s*<img//s+([^>]*)//s* img标签忽略大小写 的正则表达式的编译表示形式。
        val patternForAttrib = Pattern.compile(
            regxpForTagAttrib,
            Pattern.CASE_INSENSITIVE
        )//<//s*<img//s+([^>]*)//s*   width=忽略大小写的正则表达式的编译表示形式。
        val matcherForTag = patternForTag.matcher(str)// 创建匹配img标签的匹配器。
        val sb = StringBuffer()//缓冲区
        var result = matcherForTag.find()//查找匹配img标签的内容是否存在。
        // 循环找出每个 img 标签
        while (result) {
            val sbreplace = StringBuffer("<img  ")
            val matcherForAttrib =
                patternForAttrib.matcher(matcherForTag.group(1))//（img标签匹配器中第一个数组groupCount返回匹配器模式中的捕获组数。）的width=的匹配器
            if (matcherForAttrib.find()) {//查找匹配width属性的内容是否存在。
                matcherForAttrib.appendReplacement(sbreplace, "")//把width属性匹配器的内容换成空后的内容放入缓冲区中
            }
            matcherForAttrib.appendTail(sbreplace)//把缓冲区的内容放入width的匹配器中
            matcherForTag.appendReplacement(
                sb,
                sbreplace.toString().replace("width\\s*:\\s*\\d+px;|width\\s*:\\s*auto;".toRegex(), "")
            )//把替换后的内容替换img标签的匹配器中匹配的内容然后放入缓冲区中
            result = matcherForTag.find()//查找下一个img对应的内容
        }
        matcherForTag.appendTail(sb)//替换完成后把缓冲区的内容放入img的匹配器
        return sb.toString()
    }
}
