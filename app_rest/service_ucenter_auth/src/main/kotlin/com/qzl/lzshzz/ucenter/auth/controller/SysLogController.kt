package com.qzl.lzshzz.ucenter.auth.controller

import com.qzl.lzshzz.api.auth.SysLogControllerApi
import com.qzl.lzshzz.model.auth.SysLog
import com.qzl.lzshzz.model.auth.request.LogRequest
import com.qzl.lzshzz.ucenter.auth.service.SysLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SysLogController : SysLogControllerApi {
    @Autowired
    lateinit var sysLogService: SysLogService

    //保存日志
    @PostMapping("/saveLog")
    override fun saveLog(@RequestBody request: LogRequest):SysLog {
        return sysLogService.saveSysLog(request)
    }
}