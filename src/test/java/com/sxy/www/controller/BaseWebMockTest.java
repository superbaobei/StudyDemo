package com.sxy.www.controller;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created by xiangyusun on 2019/4/17.
 * web测试基础类,集成了几个常用注解以及变量
 */
@WebAppConfiguration//针对web进行测试,需要开启WebAppConfiguration注解
//声明spring配置文件位置,如果不指定locations,Spring-test会默认在测试类的包下查找 "类名-context.xml"作为context
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-redis.xml", "classpath:springmvc-servlet.xml"})
//@PropertySource需要注解在@Configuration的类上,如果不加@Configuration 那么属性注入不成功,或者去掉@Configuration和@PropertySource,改为单独使用@TestPropertySource
@PropertySource("classpath:config.properties")//声明自定义配置文件位置
@Configuration
public class BaseWebMockTest {

    /**
     * 注入环境,Spring默认支持注入Environment
     */
    @Autowired
    protected Environment env;

    @Autowired
    protected ServletContext context;

    protected MockMvc mockMvc;

    /**
     * spring默认可以注入的bean,不需要自己写一个bean
     */
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(context).build();
        // 或者
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

}
