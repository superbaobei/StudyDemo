package com.sxy.www.controller;

import com.sxy.www.cache.MySimpleCacheManager;
import com.sxy.www.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by xiangyusun on 2019/3/28.
 */
@Slf4j
@RestController
public class TestController {
    public TestController() {
        log.info("testController init");
    }

    @Autowired
    private MyService myService;

    @Autowired
    @Qualifier("mySimpleCacheManager")
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "healthCheck")
    public String healthCheck(){
        log.info("healthCheck");
        return "success";
    }

    @GetMapping(value = "redisConnection/{redisKey}")
    public String insertIntoRedis(@PathVariable("redisKey") String redisKey){

        redisTemplate.opsForValue().set(redisKey,redisKey);
        return "success";
    }

    @GetMapping(value = "value/{key}")
    public String getValue(@PathVariable("key") String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    @GetMapping(value = "getCacheNum")
    public Integer getCacheNum(){
        Collection<String> coll = cacheManager.getCacheNames();
        if(cacheManager instanceof MySimpleCacheManager){
            log.info("current cacheManager is MySimpleCacheManager");
        }
        Iterator<String> iterator = coll.iterator();
        Integer count = 0;
        while (iterator.hasNext()) {
            count++;
            String name = iterator.next();
            System.out.println("name = " + name);
            ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache(name);
            ConcurrentMap<Object, Object> realMap =  cache.getNativeCache();
            for (Map.Entry entry:realMap.entrySet()) {
                System.out.println("entry.getKey() = " + entry.getKey());
                System.out.println("entry.getValue() = " + entry.getValue());
            }
            System.out.println("realMap.size() = " + realMap.size());
        }
        return count;
    }

    /**
     * 自定义SimpleCacheManager的使用
     * @param key
     * @return
     */
    @GetMapping(value = "testCache/{key}")
    public String testCache(@PathVariable ("key")String key){
        return myService.cacheableMethod(key);
    }

    /**
     * 插入redis缓存
     * @param key
     * @return
     */
    @GetMapping(value = "testRedisCache/{key}")
    public String testRedisCache(@PathVariable ("key")String key){
        return myService.redisCacheableMethod(key);
    }

    /**
     * 缓存驱逐
     * @param key
     * @return
     */
    @GetMapping(value = "deleteRedisCache/{key}")
    public String deleteRedisCache(@PathVariable ("key")String key){
        return myService.deleteRedisCache(key);
    }

    /**
     * 缓存更新
     * @param key
     * @return
     */
    @GetMapping(value = "updateRedisCache/{key}")
    public String updateRedisCache(@PathVariable ("key")String key){
        return myService.updateRedisCache(key);
    }
}
