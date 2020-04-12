package com.zdww.lzshzz.app.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import java.util.stream.Collectors

/**
 * @author 强周亮
 * @desc 配置过滤路径和验证方式设置
 * @email 2538096489@qq.com
 * @time 2019/11/20 10:54
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //激活方法上的PreAuthorize注解
class ResourceServerConfig : ResourceServerConfigurerAdapter() {
    //过滤放行地址
    @Value("\${except.service_path}")
    lateinit var exceptPath:ArrayList<String>
    //定义JwtTokenStore，使用jwt令牌
    @Bean
    fun tokenStore(jwtAccessTokenConverter: JwtAccessTokenConverter?): TokenStore {
        return JwtTokenStore(jwtAccessTokenConverter)
    }

    //定义JJwtAccessTokenConverter，使用jwt令牌
    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setVerifierKey(pubKey)
        return converter
    }

    /**
     * 获取非对称加密公钥 Key
     *
     * @return 公钥 Key
     */
    private val pubKey: String?
        private get() {
            val resource: Resource = ClassPathResource(PUBLIC_KEY) as Resource
            return try {
                val inputStreamReader = InputStreamReader(resource.inputStream)
                val br = BufferedReader(inputStreamReader)
                br.lines().collect(Collectors.joining("\n"))
            } catch (ioe: IOException) {
                null
            }
        }

    //Http安全配置，对每个到达系统的http请求链接进行校验
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        //有需要额为过滤的地址，在这儿添加即可
//        exitPath.add("/**")
        //所有请求必须认证通过
        http.authorizeRequests() //下边的路径放行,"/**"可以放行所有请求路径，在开发阶段可以加上，方便测试
                .antMatchers(*exceptPath.toTypedArray()).permitAll()
                .anyRequest().authenticated()
    }

    companion object {
        //公钥
        private const val PUBLIC_KEY = "lzshzz_publickey.txt"
    }
}