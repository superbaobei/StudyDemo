package com.sxy.www.service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by xiangyusun on 2019/4/18.
 */
@Slf4j
public class StandardCodeSet implements CodeSet {

    private String code;

    @Override
    public void setEncodingName(String encodingName) {
        this.code = encodingName;
        log.info("code = {}", this.code);
    }

    @Override
    public String getDecoder(String encodingName) {
        log.info("params = {}", encodingName);
        return this.code;
    }
}
