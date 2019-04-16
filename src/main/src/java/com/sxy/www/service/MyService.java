package com.sxy.www.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MyService {

    Logger logger = LoggerFactory.getLogger(MyService.class);

    public MyService() {
        logger.info("MyService init");
    }

    @Cacheable(value = "MyService" ,cacheManager = "mySimpleCacheManager")
    public String cacheableMethod(String key){
        logger.info("模拟执行数据库操作");
        return String.valueOf(key.hashCode());
    }

    @Cacheable(value = RedisCache ,cacheManager = "cacheManager")
    public String redisCacheableMethod(String key){
        logger.info("模拟执行数据库操作");
        return String.valueOf(key.hashCode());
    }

    @CacheEvict(value = "MyService")
    public String updataCache(String key){
        logger.info("update mySimpleCacheManager's cache key = {}",key);
        return "success";
    }

    @Cacheable(value = RedisCache,key = "#key")
    public void redisCache(String key){
        logger.info("redis缓存注解的方法执行,key = {}",key);
    }

    @CacheEvict(value = RedisCache,key = "#key")
    public String deleteRedisCache(String key){
        logger.info("delete redisCache's cache key = {}",key);
        return "success";
    }

    @CachePut(value = RedisCache,key = "#key")
    public String updateRedisCache(String key){
        logger.info("update redisCache's cache key = {}",key);
        return String.valueOf(key.hashCode()) + "-updated";
    }
}
