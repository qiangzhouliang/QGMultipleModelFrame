package com.zdww.lzshzz.common.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author 强周亮
 * @desc 处理Feign调用其他系统的时候，往请求头里面加上 authorization这个参数
 * @email 2538096489@qq.com
 * @time 2019/11/5 20:38
 * @class FeginInterceptor
 * @package com.zdww.lzshzz.common.config
 */
@Configuration //RequestInterceptor
class FeginInterceptor : RequestInterceptor {

    //过滤放行地址
    @Value("\${except.path}")
    lateinit var strPath:Array<String>
    private val httpServletRequest: HttpServletRequest?
        get() {
            return try {
                (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            } catch (e: Exception) {
                null
            }

        }

    override fun apply(template: RequestTemplate) {
        val s = getHeaders(httpServletRequest!!)["authorization"]
        if (!isRelease(httpServletRequest!!) && s?.length ?: 0 > 15) {
            template.header(TOKEN_HEADER, s)
        }
    }

    private fun getHeaders(request: HttpServletRequest): Map<String, String> {
        val map = LinkedHashMap<String, String>()
        val enumeration = request.headerNames
        while (enumeration.hasMoreElements()) {
            val key = enumeration.nextElement()
            val value = request.getHeader(key)
            map[key] = value
        }
        return map
    }

    //判断需要放行的地址
    private fun isRelease(request: HttpServletRequest): Boolean {
        val requestURI = request.requestURI
        //如果包含地址，则放行通过
        return strPath.contains(requestURI)
    }

    companion object {
        var TOKEN_HEADER = "authorization"
    }

}