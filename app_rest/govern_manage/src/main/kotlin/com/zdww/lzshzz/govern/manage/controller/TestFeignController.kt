package com.zdww.lzshzz.govern.manage.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 使用ribbon实现负载均衡
 */
@RestController
class TestFeignController {
    // 使用FeignClient实现负载均衡
    @Autowired
    lateinit var restController: RestControllerIntf

    @GetMapping("/testK1")
    fun test(@RequestParam("name") name: String): String {
        //暂时用静态数据
        val test = restController.testK(name)
        return test
    }
}