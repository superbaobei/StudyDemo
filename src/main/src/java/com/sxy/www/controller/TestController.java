package com.sxy.www.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by xiangyusun on 2019/3/28.
 */
@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "healthCheck")
    public String healthCheck(){

        logger.info("healthCheck");
        return "success";
    }
}
