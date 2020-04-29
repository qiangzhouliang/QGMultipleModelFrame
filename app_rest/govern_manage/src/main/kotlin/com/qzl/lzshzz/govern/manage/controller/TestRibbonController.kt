package com.qzl.lzshzz.govern.manage.controller

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.qzl.lzshzz.govern.manage.config.ServiceListId
import com.qzl.lzshzz.util.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 使用FeignClient实现负载均衡
 */
@RestController
class TestRibbonController {
    //    restTemplate 实现负载均衡
    @Autowired
    lateinit var restTemplate: RestTemplate
    //服务端口号
    @Value("\${server.port}")
    var port: String? = null

    /**
     * 发起ribbon 请求
     */
    @RequestMapping("/**")
    @HystrixCommand(fallbackMethod = "test1Fallback")
    fun test1(request: HttpServletRequest, response: HttpServletResponse): String {
        val servletPath = request.servletPath
        //请求参数
        val params = Util.getParamter(request.parameterMap)
        //ribbon客户端从eurekaServer中获取服务列表,根据服务名获取服务列表
        val forEntity = restTemplate.getForEntity("http://${ServiceListId.APP}$servletPath$params", String::class.java)
        return forEntity?.body.toString()
    }

    fun test1Fallback(request: HttpServletRequest, response: HttpServletResponse): String {
        return "${ServiceListId.APP} 服务发生异常，调派者端口：$port"
    }
}