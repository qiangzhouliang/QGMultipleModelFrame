package com.qzl.lzshzz.app

import com.qzl.lzshzz.api.TestApi
import com.qzl.lzshzz.app.jpa.TJpaService
import com.qzl.lzshzz.app.service.DemoService
import com.qzl.lzshzz.common.exception.ExceptionCast
import com.qzl.lzshzz.common.model.response.CommonCode
import com.qzl.lzshzz.common.model.response.GetPageInfoResponse
import com.qzl.lzshzz.common.model.response.QueryResponseResult
import com.qzl.lzshzz.common.model.response.QueryResult
import com.qzl.lzshzz.model.ucenter.SysAccount
import com.qzl.lzshzz.util.StringHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController : TestApi {

    @Autowired
    lateinit var demoService: DemoService
    @Autowired
    lateinit var TJpaService: TJpaService
    @Value("\${server.port}")
    lateinit var port: String

    @GetMapping("/testK")
    override fun test(@RequestParam("name") name: String): QueryResponseResult {
        //暂时用静态数据
        //定义queryResult
        val queryResult = QueryResult<String>()
        queryResult.list = listOf("111")
        queryResult.total = 1
        //非法参数异常
        if (StringHelper.isEmptyString(name)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM)
        }

        return QueryResponseResult(CommonCode.SUCCESS, queryResult)
    }

    //测试mybatis单表查询
    @GetMapping("/testDemo")
    fun demo(@RequestParam("id") id: String): SysAccount {
        val findSysAccount = demoService.findSysAccount(id)
        return findSysAccount
    }

    //测试mybatis单表分页查询
    @GetMapping("/testAllDemo")
    fun demo(): GetPageInfoResponse<SysAccount>? {
        val findSysAccount = demoService.findAllSysAccount()
        return findSysAccount
    }

    //测试jpa单表分页查询
    @GetMapping("/testAllJpa")
    fun testAllJpa(): Page<SysAccount>? {
        val data = TJpaService.getData("宋", 0, 0, 10)
        return data
    }
}