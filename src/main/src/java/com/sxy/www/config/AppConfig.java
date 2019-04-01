package com.sxy.www.config;

import com.sxy.www.cache.MySimpleCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by xiangyusun on 2019/3/29.
 */
@Component
@Configuration
@EnableCaching
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public AppConfig() {
        logger.info(AppConfig.class.getName() + " init");
    }

    @Bean
    @Qualifier("concurrentMapCacheManager")
    public ConcurrentMapCacheManager getConcurrentMapCacheManager(){
        return new ConcurrentMapCacheManager();
    }

//    @Bean
//    @Qualifier("cacheManager")
//    public SimpleCacheManager getSimpleCacheManager(){
//        return new SimpleCacheManager();
//    }

    @Bean
    @Primary
    @Qualifier("cacheManager")
    public MySimpleCacheManager getMySimpleCacheManager(){
        MySimpleCacheManager cacheManager = new MySimpleCacheManager();
        return cacheManager;
    }

//    public RedisCacheManager getRedisCacheManager(){
//        RedisCacheManager manager = new RedisCacheManager();
//    }

}
