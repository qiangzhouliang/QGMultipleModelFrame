package com.qzl.lzshzz.util

import com.sun.glass.ui.Application
import org.jasypt.encryption.StringEncryptor
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class])
class UtilApplicationTests {

    //加密
    @Test
    fun getPassword() {
    }

}