package com.sxy.www.cache;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.core.Ordered;

/**
 * Created by xiangyusun on 2019/3/29.
 * 支持创建不存在的缓存名称的SimpleCacheManager,重写了{@link AbstractCacheManager#getMissingCache(java.lang.String)}方法
 */
@Slf4j
public class MySimpleCacheManager extends SimpleCacheManager implements BeanPostProcessor, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(MySimpleCacheManager.class);

    public MySimpleCacheManager() {
        logger.info("MySimpleCacheManager new instance");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName = {}", beanName);
        log.info("前置执行");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName = {}", beanName);
        log.info("后置执行");
        return bean;
    }

    @Override
    protected Cache getMissingCache(String name) {
        logger.info("create missing cache name :{}",name);
        return new ConcurrentMapCache(name);
    }

    public void init() {
        log.info("MySimpleCacheManager bean init method executed");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}