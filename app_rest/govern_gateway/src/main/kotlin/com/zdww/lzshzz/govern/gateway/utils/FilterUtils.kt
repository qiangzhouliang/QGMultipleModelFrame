package com.zdww.lzshzz.govern.gateway.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.netflix.zuul.context.RequestContext
import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.common.model.response.ResponseResult
import org.apache.commons.lang.StringUtils
import java.io.UnsupportedEncodingException
import javax.servlet.http.HttpServletRequest


object FilterUtils {
    private val ENCODING = "UTF-8"

    var specialCharArr = arrayOf("%22", "&#x", "%27", "%28", "%2B", "%2F", "%3C", "%3D", "%3E", "\\\"", "\\'", "script", "alert", "prompt", "javascript", "__.", "/..", "\\..", "/**", "\\*", "DECLARE", "<iframe", "Content-Type:", "window.open", "onload=", "xwork2", "ProcessBuilder", "valueOf:", ";sxcqka(")
    //判断request参数是否含有特殊字符
    fun injectInput(request: HttpServletRequest): Boolean {
        try {
            request.characterEncoding = ENCODING
        } catch (e1: UnsupportedEncodingException) {
        }

        val e = request.parameterNames
        val res = false

        while (e.hasMoreElements()) {
            val attributeName = e.nextElement() as String

            val attributeValues = request.getParameterValues(attributeName)
            for (i in attributeValues.indices) {
                if (!StringUtils.isEmpty(attributeValues[i]) && injectChar(attributeValues[i])) {
                    return true
                }
            }
        }
        return res
    }

    //判断json参数是否含有特殊字符
    fun injectInputJson(json: JSONObject): Boolean {
        val res = false
        for ((key, value) in json) {
            if (!StringUtils.isEmpty(value as String?) && injectChar(value as String)) {
                return true
            }
        }
        return res
    }

    /**
     * @Author 强周亮
     * @Description 判断是否含有特殊字符
     * @Date 17:04 2019/11/15
     **/
    fun injectChar(str: String): Boolean {
        var res = false
        for (i in specialCharArr.indices) {
            if (StringUtils.contains(str.toUpperCase(), specialCharArr[i].toUpperCase())) {
                res = true
                break
            }
        }
        return res
    }

    //拒绝访问
    fun accessDenied(commonCode: CommonCode) {
        val requestContext = RequestContext.getCurrentContext()
        //得到response
        val response = requestContext.response
        //拒绝访问
        requestContext.setSendZuulResponse(false)
        //设置响应代码
        requestContext.responseStatusCode = 200
        //构建响应的信息CommonCode.UNAUTHENTICATED
        val responseResult = ResponseResult(CommonCode.UNAUTHENTICATED)
        //转成json
        val jsonString = JSON.toJSONString(responseResult)
        requestContext.responseBody = jsonString
        //转成json，设置contentType
        response.contentType = "application/json;charset=utf-8"
    }
}
