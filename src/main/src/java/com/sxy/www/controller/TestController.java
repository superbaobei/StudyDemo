package com.sxy.www.controller;

import com.sxy.www.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiangyusun on 2019/3/28.
 */
@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MyService myService;

    @GetMapping(value = "healthCheck")
    @ResponseBody
    public String healthCheck(){
        logger.info("healthCheck");
        return "success";
    }
}
