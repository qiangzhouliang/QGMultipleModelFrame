package com.qzl.lzshzz.govern.gateway.filter

import com.alibaba.fastjson.JSON
import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.exception.ZuulException
import com.qzl.lzshzz.common.model.response.CommonCode
import com.qzl.lzshzz.govern.gateway.service.AuthService
import com.qzl.lzshzz.govern.gateway.utils.FilterUtils.accessDenied
import com.qzl.lzshzz.govern.gateway.utils.FilterUtils.injectInput
import com.qzl.lzshzz.govern.gateway.utils.FilterUtils.injectInputJson
import com.qzl.lzshzz.util.StringHelper
import org.apache.commons.io.IOUtils
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException


/** 身份校验过虑器
 * @author Administrator
 * @version 1.0
 */

@Component
class LoginFilter() : ZuulFilter() {

    @Autowired
    lateinit var authService: AuthService
    //过滤放行地址
    @Value("\${except.path}")
    lateinit var strPath:Array<String>

    //过虑器的类型
    override fun filterType(): String {
        return "pre"
    }

    //过虑器序号，越小越被优先执行
    override fun filterOrder(): Int {
        return 0
    }

    override fun shouldFilter(): Boolean {
        //返回true表示要执行此过虑器
        return true
    }

    //过虑器的内容
    //测试的需求：过虑所有请求，判断头部信息是否有Authorization，如果没有则拒绝访问，否则转发到微服务。
    @Throws(ZuulException::class)
    override fun run(): Any? {
        val request = RequestContext.getCurrentContext().request
        val servletPath = request.servletPath

        //如果包含地址，则放行通过
        if (strPath.contains(servletPath)) {
            return null
        }
        //从request中获取UID
        var uid: String? = request.getParameter("uid")
        if (!StringHelper.isEmptyString(uid)) {
            if (injectInput(request)) {
                //拒绝访问
                accessDenied(CommonCode.INVALID_PARAM)
                return null
            }
        } else {
            val ctx = RequestContext.getCurrentContext()
            if (!ctx.isChunkedRequestBody) {
                try {
                    val inp = ctx.request.inputStream
                    if (inp != null) {
                        val body = IOUtils.toString(inp,"UTF-8")
                        val json = JSON.parseObject(body)
//                        val json = JSONObject.parseObject(body)
                        if (injectInputJson(json)) {
                            //拒绝访问
                            accessDenied(CommonCode.INVALID_PARAM)
                            return null
                        }
                        uid = json["uid"] as String?
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        //从header中取jwt
        val jwtFromHeader = authService.getJwtFromHeader(request)
        if (StringUtils.isEmpty(jwtFromHeader)) {
            //拒绝访问
            accessDenied(CommonCode.UNAUTHENTICATED)
            return null
        }
        //从redis取出jwt的过期时间
        val expire = authService.getExpire(uid,true)
        if (expire < 1) {
            //拒绝访问
            accessDenied(CommonCode.UNAUTHENTICATED)
            return null
        }

        return null
    }
}
