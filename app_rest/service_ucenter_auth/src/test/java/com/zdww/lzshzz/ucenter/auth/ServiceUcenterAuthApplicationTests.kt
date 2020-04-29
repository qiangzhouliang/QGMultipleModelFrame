package com.zdww.lzshzz.ucenter.auth

import com.sun.glass.ui.Application
import com.zdww.lzshzz.common.Constants
import org.jasypt.encryption.StringEncryptor
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class])
class ServiceUcenterAuthApplicationTests {
    @Autowired
    var encryptor: StringEncryptor? = null

    //加密
    @Test
    fun getPassword() {
        /**  配置加解密跟秘钥，与配置文件的密文分开放  */
        System.setProperty("jasypt.encryptor.password", Constants.JASYPT_ENCRYPTOR_PASSWORD);
        val mysql_test_url = encryptor!!.encrypt("jdbc:mysql://localhost:3306/app_rest?serverTimezone=UTC&useUnicode=true&autoReconnect=true&failOverReadOnly=false&useSSL=false&characterEncoding=utf8")
        val mysql_test_name = encryptor!!.encrypt("root")
        val mysql_test_pass = encryptor!!.encrypt("root")
        val redis_host = encryptor!!.encrypt("10.18.100.205")
        println("mysql_test_url=$mysql_test_url\n")
        println("mysql_test_name=$mysql_test_name\n")
        println("mysql_test_pass=$mysql_test_pass\n")
        println("redis_host=$redis_host\n")
    }

    //解密
    @Test
    fun getDecode() {
        /**  配置加解密跟秘钥，与配置文件的密文分开放  */
        System.setProperty("jasypt.encryptor.password", Constants.JASYPT_ENCRYPTOR_PASSWORD);
        val mysql_test_url = encryptor!!.decrypt("G410d5s8wexZq8XSe0v56ORcNy1lb57NVqQNsyXRhyZ05PMoZxQ9mfoFH9+9cxuvpIKvsWeLAcaZTcfJEFi/3POWfDg3Smv8jw1YUFmxyS4v9+/TLGa56/HnWPyvfW6yMsND01fh3gaCHr72eeUEPsx6npqcpg6+3jL1xo6r0qIazmVjNkzTzcFewamvLO6F")
        val mysql_test_name = encryptor!!.decrypt("38Tq10C0wLF0Y2waK5885A==")
        val mysql_test_pass = encryptor!!.decrypt("+GjDB2d0eV1szaePyrBhNvODvDWtVmtO")
        val redis_host = encryptor!!.decrypt("M3v5Lzoc/JzAYQ8o/ndmX2RO4zj58m4q")
        println("hzz_wx_url=$mysql_test_url\n")
        println("hzz_wx_name=$mysql_test_name\n")
        println("hzz_wx_pass=$mysql_test_pass\n")
        println("redis_host=$redis_host\n")
    }
}