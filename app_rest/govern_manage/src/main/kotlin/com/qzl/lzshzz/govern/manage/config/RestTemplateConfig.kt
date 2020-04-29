package com.qzl.lzshzz.govern.manage.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    /**
     * 构建一个使用默认配置RestTemplate Bean
     */
    @Bean
    @LoadBalanced
    fun restTemplate(): RestTemplate {
        return RestTemplate(OkHttp3ClientHttpRequestFactory())
    }
}