package com.sxy.www.controller;

import com.sxy.www.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by xiangyusun on 2019/3/28.
 */
@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MyService myService;

    @Autowired
    @Qualifier("cacheManager")
    private CacheManager cacheManager;

    @GetMapping(value = "healthCheck")
    @ResponseBody
    public String healthCheck(){
        logger.info("healthCheck");
        return "success";
    }

    @GetMapping(value = "getCacheNum")
    @ResponseBody
    public Integer getCacheNum(){
        Collection<String> coll = cacheManager.getCacheNames();
        Iterator<String> iterator = coll.iterator();
        Integer count = 0;
        while (iterator.hasNext()) {
            count++;
            String name = iterator.next();
            System.out.println("name = " + name);
            ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache(name);
            ConcurrentMap<Object, Object> realMap =  cache.getNativeCache();
            System.out.println("realMap.size() = " + realMap.size());
        }
        return count;
    }

    @GetMapping(value = "testCache/{key}")
    @ResponseBody
    public String testCache(@PathVariable ("key")String key){
        return myService.cacheableMethod(key);
    }
}
