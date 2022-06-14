package com.wuyou.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description redis 工具类
 * @Author 无忧
 * @Date 2022/4/14 15:50
 */
@Component
public class RedisUtil {


    @Autowired
    private StringRedisTemplate redisTemplate;


    public ValueOperations stringOper(){
        return redisTemplate.opsForValue();
    }
    public void setValue(String key, Object value){
        stringOper().set(key, value);
    }

    public void setValue(String key, Object value, long timeout){
        stringOper().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public Object getValue(String key, long timeout){
        return stringOper().getAndExpire(key, timeout,  TimeUnit.SECONDS);
    }

    public Object getValue(String key){
        return stringOper().get(key);
    }
}
