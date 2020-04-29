package com.qzl.lzshzz.govern.manage.controller

import com.qzl.lzshzz.govern.manage.exception.RestControllersHystrix
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "APP", fallback = RestControllersHystrix::class)  //指定远程调用的服务名
interface RestControllerIntf {
    //根据页面id查询页面信息，远程调用cms请求数据
    @GetMapping("/testK")//这里的请求路径需要和APP中的请求路径一致
    fun testK(@RequestParam("name") name: String): String//这里的方法名需要和eureka-client中的方法名一致
}