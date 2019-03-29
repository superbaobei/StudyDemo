package com.sxy.www.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;

/**
 * Created by xiangyusun on 2019/3/29.
 */
public class MySimpleCacheManager extends SimpleCacheManager {

    private static final Logger logger = LoggerFactory.getLogger(MySimpleCacheManager.class);

    public MySimpleCacheManager() {
        logger.info("MySimpleCacheManager new instance");
    }

    @Override
    protected Cache getMissingCache(String name) {
        logger.info("create missing cache name :{}",name);
        return new ConcurrentMapCache(name);
    }

}