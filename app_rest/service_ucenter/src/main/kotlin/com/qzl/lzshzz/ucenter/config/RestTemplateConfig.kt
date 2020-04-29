package com.qzl.lzshzz.ucenter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    /**
     * 构建一个使用默认配置RestTemplate Bean
     * 增加@LoadBalanced，就不能使用127.0.0.1,只能使用应用名(等于上图的fqyd-member不区分大小写)
     */
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate(OkHttp3ClientHttpRequestFactory())
    }
}