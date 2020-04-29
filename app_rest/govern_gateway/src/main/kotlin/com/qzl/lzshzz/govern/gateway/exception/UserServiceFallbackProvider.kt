package com.qzl.lzshzz.govern.gateway.exception

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets

@Component
class UserServiceFallbackProvider : FallbackProvider {
    override fun fallbackResponse(route: String, cause: Throwable): ClientHttpResponse {
        if (cause.cause != null) {
            val reason = cause.cause!!.message
            //输出详细的回退原因
            println("接口：$route,fallback reason: {}$reason")
        }
        return object : ClientHttpResponse {
            override fun getBody(): InputStream {
                // 当出现服务调用错误之后返回的数据内容,可以做一些短信通知等功能
                return ByteArrayInputStream("{\"success\":false,\"code\":-1,\"message\":\"服务暂不可用 => ${route}\"}".toByteArray(StandardCharsets.UTF_8))
            }

            override fun getHeaders(): HttpHeaders {
                val headers = HttpHeaders()
                //和body中的内容编码一致，否则容易乱码
                headers.contentType = MediaType.APPLICATION_JSON_UTF8
                return headers
            }

            /**
             * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的，
             * 不应该把api的404,500等问题抛给客户端
             * 网关和api服务集群对于客户端来说是黑盒子
             */
            override fun getStatusCode(): HttpStatus {
                return HttpStatus.BAD_REQUEST
            }

            override fun getRawStatusCode(): Int {
                return HttpStatus.BAD_REQUEST.value()
            }

            override fun getStatusText(): String {
                return HttpStatus.BAD_REQUEST.reasonPhrase
            }

            override fun close() {}
        }
    }

    override fun getRoute(): String {
        return "*"
    }
}