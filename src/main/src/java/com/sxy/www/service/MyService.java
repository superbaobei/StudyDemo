package com.sxy.www.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xiangyusun on 2019/3/28.
 */
@Service
public class MyService {
    Logger logger = LoggerFactory.getLogger(MyService.class);
    public MyService() {
        logger.info("MyService init");
    }
}
