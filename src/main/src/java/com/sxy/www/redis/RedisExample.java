package com.sxy.www.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * Created by xiangyusun on 2019/4/1.
 */
public class RedisExample {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml","classpath:applicationContext-redis.xml");
        RedisTemplate<String,String> redisTemplate = applicationContext.getBean(RedisTemplate.class);
        Set<String> set = redisTemplate.keys("*");
        System.out.println("size = " + set.size());
    }
}
