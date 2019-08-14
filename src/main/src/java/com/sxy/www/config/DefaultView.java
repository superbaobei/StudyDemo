package com.sxy.www.config;

import com.sxy.www.interceptor.MyHandlerInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class DefaultView extends WebMvcConfigurerAdapter {

    @Autowired
    private MyHandlerInterceptorAdapter myHandlerInterceptorAdapter;

    @Bean
    public MyHandlerInterceptorAdapter myHandlerInterceptorAdapter() {
        return new MyHandlerInterceptorAdapter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("注册interceper");
        registry.addInterceptor(myHandlerInterceptorAdapter).addPathPatterns("/demo/healthCheck");
    }
}
