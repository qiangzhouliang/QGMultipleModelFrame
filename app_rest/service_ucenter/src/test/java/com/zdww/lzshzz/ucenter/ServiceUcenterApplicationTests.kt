package com.zdww.lzshzz.ucenter

import com.zdww.lzshzz.ucenter.service.SysDeptService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ServiceUcenterApplicationTests {

    @Autowired
    lateinit var sysDeptService: SysDeptService

    @Test
    fun contextLoads() {
        val deptInfo = sysDeptService.getDeptInfo("1824")
        println(deptInfo?.deptName)
    }

}
