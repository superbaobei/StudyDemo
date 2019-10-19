package com.sxy.www.controller;

import com.sxy.www.service.CodeSet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ServiceLoader;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by xiangyusun on 2019/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)

@Slf4j//开启lombok
public class TestControllerTest extends BaseWebMockTest {

    @Autowired
    TestController testController;
    private static ServiceLoader<CodeSet> codecSetLoader
            = ServiceLoader.load(CodeSet.class);
    @Test
    @Timed(millis = 1000)//被注解的方法需要在1s内完成,,否则抛出异常
    @Repeat(2)//重复执行10次
    public void getArticleListTest() throws Exception {
        codecSetLoader.iterator().next().setEncodingName("大萨达所");
        System.out.println(codecSetLoader.iterator().next().getDecoder("dsad"));
        log.info("url={}", env.getProperty("database.ip"));
        testController.testRedisCache("dsad");
        //发送请求
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/testRedisCache/{k}", "dsadsd").accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + result);
    }

}