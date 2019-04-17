package com.sxy.www.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import static com.sxy.www.utils.Constants.RedisCache;

/**
 * Created by xiangyusun on 2019/3/28.
 */
@Service
@Configuration
@Slf4j
public class MyService {

    public MyService() {
        log.info("MyService init");
    }

    @Cacheable(value = "MyService" ,cacheManager = "mySimpleCacheManager")
    public String cacheableMethod(String key){
        log.info("模拟执行数据库操作");
        return String.valueOf(key.hashCode());
    }

    @Cacheable(value = RedisCache ,cacheManager = "cacheManager")
    public String redisCacheableMethod(String key){
        log.info("模拟执行数据库操作");
        return String.valueOf(key.hashCode());
    }

    @CacheEvict(value = "MyService")
    public String updataCache(String key){
        log.info("update mySimpleCacheManager's cache key = {}",key);
        return "success";
    }

    @Cacheable(value = RedisCache,key = "#key")
    public void redisCache(String key){
        log.info("redis缓存注解的方法执行,key = {}",key);
    }

    @CacheEvict(value = RedisCache,key = "#key")
    public String deleteRedisCache(String key){
        log.info("delete redisCache's cache key = {}",key);
        return "success";
    }

    @CachePut(value = RedisCache,key = "#key")
    public String updateRedisCache(String key){
        log.info("update redisCache's cache key = {}",key);
        return String.valueOf(key.hashCode()) + "-updated";
    }
}
