package com.qzl.lzshzz.ucenter.auth.service;

import com.qzl.lzshzz.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author 强周亮
 * @desc redis 服务调运
 * @email 2538096489@qq.com
 * @time 2019/11/29 21:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedisService {
    @Autowired
    StringRedisTemplate redisTemplate;

    //存储登录过期次数到redis
    public boolean saveData(String userAccount, String loginNum, long ttl) {
        //只设置内容，不设置过期时间
        String key = "login_num:"+userAccount;
        redisTemplate.boundValueOps(key).set(loginNum, ttl, TimeUnit.SECONDS);
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire > 0 || expire == -1;
    }

    //查询redis过期时间
    public Long getRedisTime(String userAccount) {
        String key = "login_num:"+userAccount;
        return redisTemplate.getExpire(key, TimeUnit.MINUTES);
    }

    //删除token
    public boolean delLoginNum(String userAccount) {
        String key = "login_num:"+userAccount;
        return redisTemplate.delete(key);
    }

    //从redis查询登录次数
    public int getLoginNum(String userAccount) {
        //从redis中取到令牌信息
        String key = "login_num:"+userAccount;
        String value = (String) redisTemplate.opsForValue().get(key);
        if (StringHelper.isEmptyString(value)) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }

    //从redis查询数据
    public String getData(String userAccount) {
        //从redis中取到令牌信息
        String key = "login_num:"+userAccount;
        return (String) redisTemplate.opsForValue().get(key);
    }
}
