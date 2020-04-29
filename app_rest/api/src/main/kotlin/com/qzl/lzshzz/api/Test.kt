package com.qzl.lzshzz.api

import com.qzl.lzshzz.common.model.response.QueryResponseResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation

@Api(value = "测试接口", description = "框架搭建测试接口")
interface TestApi {
    @ApiOperation(value = "测试方法")
    @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")
    fun test(name: String): QueryResponseResult
}