package com.zdww.lzshzz.service_file

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient  //一个EurekaClient从EurekaServer发现服务
@SpringBootApplication
@EntityScan("com.zdww.lzshzz.model") //扫描实体类
@ComponentScan("com.zdww.lzshzz")//根据自己需要填写包名
class ServiceFileApp

fun main(args: Array<String>) {
    SpringApplication.run(ServiceFileApp::class.java, *args)
}