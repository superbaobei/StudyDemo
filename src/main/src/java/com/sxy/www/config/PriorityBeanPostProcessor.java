package com.sxy.www.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by xiangyusun on 2019/4/22.
 */
@Slf4j
@Component
public class PriorityBeanPostProcessor implements BeanPostProcessor, Ordered, InitializingBean {

    /**
     * 所有的bean都会经过这个BeanPostProcessor进行处理,可以在内部进行区分,针对某一个bean进行处理
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("order = 0,bean = {}的前置方法", beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("order = 0,bean =  {}的后置方法", beanName);
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("PriorityBeanPostProcessor 的InitializingBean接口的afterPropertiesSet方法被调用");
    }
}
