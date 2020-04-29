package com.qzl.lzshzz.ucenter.client

import com.qzl.lzshzz.common.client.ServiceList
import com.qzl.lzshzz.model.auth.SysLog
import com.qzl.lzshzz.model.auth.request.LogRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * @author 强周亮
 * @desc 用户认证客户端
 * @email 2538096489@qq.com
 * @time 2019/11/7 12:03
 */
@FeignClient(value = ServiceList.SERVICE_UCENTER_AUTH)
interface AuthClient {
    @PostMapping("/auth/saveLog")
    fun saveLog(@RequestBody request: LogRequest): SysLog
}