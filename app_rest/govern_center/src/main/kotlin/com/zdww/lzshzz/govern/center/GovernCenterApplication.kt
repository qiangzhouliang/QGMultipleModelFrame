package com.zdww.lzshzz.govern.center

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

/**
 * EurekaServer 服务，服务管理者
 */
@EnableEurekaServer  //标识此工程是一个EurekaServer
@SpringBootApplication
class GovernCenterApplication

fun main(args: Array<String>) {
    SpringApplication.run(GovernCenterApplication::class.java, *args)
}