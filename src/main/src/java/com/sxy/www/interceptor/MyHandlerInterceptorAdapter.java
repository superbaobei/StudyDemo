package com.sxy.www.interceptor;

import com.sxy.www.model.User;
import com.sxy.www.utils.AuthClient;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    private static int count = 0;

    public MyHandlerInterceptorAdapter() {
        count++;
        System.out.println("第" + count + "次构造方法调用");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = AuthClient.getUser();
        if (null == user) {
            System.out.println("user is null");
        } else {
            System.out.println("user is exist");
        }
        return super.preHandle(request, response, handler);
    }
}
