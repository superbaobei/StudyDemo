package com.sxy.www.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

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

    @Cacheable(value = "MyService")
    public String cacheableMethod(String key){
        logger.info("模拟执行数据库操作");
        return String.valueOf(key.hashCode());
    }
}
