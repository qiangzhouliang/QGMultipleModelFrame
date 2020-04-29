package com.qzl.lzshzz.govern.gateway

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.ComponentScan

/**
 * @author 强周亮
 * @desc 此工程是个路由网关服务，是一个zuul网关
 * @email 2538096489@qq.com
 * @time 2019/8/20 21:54
 */
@SpringBootApplication
@EnableZuulProxy //此工程是一个zuul网关
@ComponentScan("com.qzl.lzshzz")//扫描实体类
class GovernGatewayApplication

fun main(args: Array<String>) {
    SpringApplication.run(GovernGatewayApplication::class.java, *args)
}

