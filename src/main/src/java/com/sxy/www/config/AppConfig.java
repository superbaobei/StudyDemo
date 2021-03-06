package com.sxy.www.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.sxy.www.cache.MySimpleCacheManager;
import com.sxy.www.servlet.ApolloTestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by xiangyusun on 2019/3/29.
 */
@Component
@Configuration
@EnableCaching//开启缓存
@EnableApolloConfig//apollo集成
@Import(DynamicRegistrar.class)
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public AppConfig() {
        logger.info(AppConfig.class.getName() + " init");
    }

//    @Bean
//    @Qualifier("concurrentMapCacheManager")
//    public ConcurrentMapCacheManager getConcurrentMapCacheManager(){
//        return new ConcurrentMapCacheManager();
//    }

//    @Bean
//    @Qualifier("cacheManager")
//    public SimpleCacheManager getSimpleCacheManager(){
//        return new SimpleCacheManager();
//    }

    @Bean(initMethod = "init")
    public MySimpleCacheManager mySimpleCacheManager(){
        MySimpleCacheManager mySimpleCacheManager = new MySimpleCacheManager();
        return mySimpleCacheManager;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        vendorAdapter.setGenerateDdl(true);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
////        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.acme.domain");
//        factory.setDataSource(dataSource());
//        return factory;
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }

    @Bean(name = "cacheManager")
    @Primary
    public RedisCacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return stringRedisTemplate;
    }

    @Bean
    public ApolloTestBean apolloTestBean() {
        return new ApolloTestBean();
    }

}
