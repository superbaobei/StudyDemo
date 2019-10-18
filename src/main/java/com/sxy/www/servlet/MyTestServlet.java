package com.sxy.www.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiangyusun on 2019/3/28.
 */
public class MyTestServlet extends HttpServlet implements Servlet{

    Logger logger = LoggerFactory.getLogger(MyTestServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println(" MyTestServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        logger.info("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        logger.info("doPost");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("service");
    }

    @Override
    public void destroy() {
        logger.info("destory");
    }
}
