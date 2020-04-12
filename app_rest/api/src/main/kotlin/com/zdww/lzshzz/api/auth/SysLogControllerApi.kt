package com.zdww.lzshzz.api.auth

import com.zdww.lzshzz.model.auth.SysLog
import com.zdww.lzshzz.model.auth.request.LogRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * 日志操作类
 */
@Api(value = "日志操作", description = "日志操作接口")
interface SysLogControllerApi {
    @ApiOperation("保存日志方法")
    fun saveLog(request: LogRequest): SysLog
}