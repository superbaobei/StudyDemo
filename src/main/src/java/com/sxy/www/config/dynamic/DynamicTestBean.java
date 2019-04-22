package com.sxy.www.config.dynamic;

import com.sxy.www.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * Created by xiangyusun on 2019/4/18.
 */
@Slf4j
public class DynamicTestBean implements BeanPostProcessor, InitializingBean, Ordered {
    public DynamicTestBean() {
        log.info("DynamicTestBean init");
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Autowired
    private MyService myService;

    public MyService getMyService() {
        return myService;
    }

    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    public void print(String str) {
        log.info(" str = {}", str);
        if (myService == null) {
            log.error("myService is null");
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName = {}", beanName);
        log.info("postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName = {}", beanName);
        log.info("postProcessAfterInitialization");
        return bean;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("DynamicTestBean's afterPropertiesSet method has been executed");
    }
}
