package com.sxy.www.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by xiangyusun on 2019/4/24.
 * 用户创建转让标后要想esb中发送的消息
 */
@Data
@Slf4j
public class TransferBidMessage {
    {
        log.info("实例块执行");
    }

    private Long uid;

    /**
     * 项目id
     */
    private Long bidId;

    /**
     * 给api.e使用的消息类型
     */
    private final String msgType = "user_create_transfer";

    private String version;

    public TransferBidMessage(Long uid, Long bidId, String version) {
        this.uid = uid;
        this.bidId = bidId;
        this.version = version;
        log.info("构造函数执行");
    }

    static {
        log.info("静态块执行");
    }
}
