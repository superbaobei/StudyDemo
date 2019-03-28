package com.sxy.www.filter;


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
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
