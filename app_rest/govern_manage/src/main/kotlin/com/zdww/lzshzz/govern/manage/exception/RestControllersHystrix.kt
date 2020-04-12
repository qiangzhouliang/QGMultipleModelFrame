package com.zdww.lzshzz.govern.manage.exception

import com.zdww.lzshzz.govern.manage.controller.RestControllerIntf
import org.springframework.stereotype.Component

@Component
class RestControllersHystrix : RestControllerIntf {
    override fun testK(name: String): String {
        return "APP 服务发生异常,接口方法：testK(name: String)"
    }
}
