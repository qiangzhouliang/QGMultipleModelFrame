package com.zdww.lzshzz.ucenter.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author 强周亮
 * @desc 用户认证中心
 * @email 2538096489@qq.com
 * @time 2019/8/17 17:45
 * @class ServiceUcenterAuthApplication
 * @package com.qzl.ucenter.auth
 */
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan("com.zdww.lzshzz.model.auth") //扫描实体类
@ComponentScan("com.zdww.lzshzz")
@SpringBootApplication
public class UcenterAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterAuthApplication.class, args);
    }
}
