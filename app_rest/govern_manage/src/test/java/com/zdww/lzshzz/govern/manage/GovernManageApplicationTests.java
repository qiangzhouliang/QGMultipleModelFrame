package com.zdww.lzshzz.govern.manage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GovernManageApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testRibbon() {
        //确定要获取的服务名称
        String serviceID = "APP";
        //ribbon客户端从eurekaServer中获取服务列表,根据服务名获取服务列表

        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://" + serviceID + "/testK?name=5a754adf6abb500ad05688d9", Map.class);
        Map body = forEntity.getBody();
        System.out.println(body);
    }

    @Test
    public void contextLoads() {
    }

}
