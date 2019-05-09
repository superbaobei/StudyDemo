package com.sxy.www.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sxy.www.model.TransferBidMessage;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiangyusun on 2019/4/23.
 */
@Data
public class AllTest {
    @Test
    public void testConstructor() {
        ConstructorUsedField constructorUsedField = new ConstructorUsedField();
    }

    @Test
    public void testJoin() {
        String st = StringUtils.join(Arrays.asList(123, 21321, 2321), "|");
        System.out.println("st = " + st);
    }

    @Test
    public void testDate() {
        AllTest allTest = new AllTest();
        allTest.setAmount(100);
        allTest.setBidId(12321312);
        allTest.setPeriodsUnit(1);
    }

    @Test
    public void testJson() {
        TransferBidMessage message = new TransferBidMessage(33386522L, 50000006616L, "1.1");
        String str = JSONObject.toJSONString(message);
        System.out.println(str);
        JSONObject json = JSON.parseObject(str);
        String s = json.getString("msgType");
        System.out.println("s = " + s);
    }

    @Override
    public String toString() {
        return
                "uid=" + uid +
                        ", bidId=" + bidId +
                        ", periods=" + periods +
                        ", periodsUnit=" + periodsUnit +
                        ", investAnnualRate=" + investAnnualRate +
                        ", amount=" + amount;
    }

    @Test
    public void testFilter() {
        List<Integer> list = Arrays.asList(123, 32, 1);
        List<Integer> temp = list.stream().filter(a -> quartzLock(a)).collect(Collectors.toList());
        System.out.println("temp.toString() = " + temp.toString());
    }

    public static boolean quartzLock(int amount) {

        if (123 == amount) return true;
        else return false;
    }

    private Long uid;

    /**
     * 项目id
     */
    private long bidId;

    /**
     * 期限
     */
    private int periods; // 天

    /**
     * 期限单位  1：月，2：天
     */
    private int periodsUnit;

    /**
     * 年化收益率
     */
    private int investAnnualRate;

    /**
     * 金额
     */
    private long amount;
}
