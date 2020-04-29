package com.qzl.lzshzz.util

import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by admin on 2018/3/18.
 */
object CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期 以秒为单位
     */
    @JvmStatic
    fun addCookie(response: HttpServletResponse, domain: String?, path: String?, name: String?,
                  value: String?, maxAge: Int?, httpOnly: Boolean?) {
        val cookie = Cookie(name, value)
        cookie.domain = domain
        cookie.path = path
        cookie.maxAge = maxAge ?: -1
        cookie.isHttpOnly = httpOnly ?: false
        response.addCookie(cookie)
    }

    /**
     * 根据cookie名称读取cookie
     * @param request
     * @return map<cookieName></cookieName>,cookieValue>
     */
    @JvmStatic
    fun readCookie(request: HttpServletRequest, vararg cookieNames: String): Map<String, String> {
        val cookieMap = HashMap<String, String>()
        val cookies = request.cookies
        if (cookies != null) {
            for (cookie in cookies) {
                val cookieName = cookie.name
                val cookieValue = cookie.value
                for (i in cookieNames.indices) {
                    if (cookieNames[i] == cookieName) {
                        cookieMap[cookieName] = cookieValue
                    }
                }
            }
        }
        return cookieMap
    }
}
