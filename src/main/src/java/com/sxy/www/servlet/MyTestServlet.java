package com.sxy.www.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by xiangyusun on 2019/3/28.
 */
public class MyTestServlet implements Servlet{

    Logger logger = LoggerFactory.getLogger(MyTestServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info(" MyTestServlet init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
