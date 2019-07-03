package com.sxy.www.filter;


import com.sxy.www.model.User;
import com.sxy.www.utils.AuthClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xiangyusun on 2019/3/28.
 */
public class MyTestFilter implements Filter{
    Logger logger = LoggerFactory.getLogger(MyTestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("MyTestFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("do MyTestFilter");
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String uri = req.getRequestURI();
        logger.info("uid = {}",uri);
        User user = new User();
        user.setName("111");
        AuthClient.setUser(user);
        filterChain.doFilter(servletRequest,servletResponse);
        AuthClient.removeUser();
    }

    @Override
    public void destroy() {

    }
}
