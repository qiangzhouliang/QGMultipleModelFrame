package com.qzl.lzshzz.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient  //一个EurekaClient从EurekaServer发现服务
@SpringBootApplication
@EntityScan("com.qzl.lzshzz.model") //扫描实体类
@ComponentScan("com.qzl.lzshzz")//根据自己需要填写包名
class AppApplication

fun main(args: Array<String>) {
    SpringApplication.run(AppApplication::class.java, *args)
}
