package com.sxy.www.config;

import com.sxy.www.config.dynamic.DynamicTestBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by xiangyusun on 2019/4/18.
 */
public class DynamicRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(DynamicTestBean.class).getBeanDefinition();
        registry.registerBeanDefinition("dynamicTestBean", beanDefinition);
    }
}
