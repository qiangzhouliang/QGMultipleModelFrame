package com.zdww.lzshzz.govern.manage

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

/**
 * 使用FeignClient实现负载均衡实现
 */
@EnableFeignClients  //开启FeignClient
@EnableDiscoveryClient  //一个EurekaClient从EurekaServer发现服务
@SpringBootApplication
@ComponentScan("com.zdww.lzshzz")//根据自己需要填写包名
@EnableHystrix               //开启熔断机制
class GovernManageApplication

fun main(args: Array<String>) {
    SpringApplication.run(GovernManageApplication::class.java, *args)
}


