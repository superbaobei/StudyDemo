package com.sxy.www.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ShiroController
 * @Description TODO
 * @Author sunxiangyu
 * @Email sunxiangyu@huli.com
 * @Date 2019-10-18 17:57
 * @Version 1.0
 **/
@RestController
@Slf4j
public class ShiroController {

    @GetMapping("success")
    public String loginSuccess() {
        log.info("loginSuccess");
        return "login success";
    }

    @GetMapping("login")
    public String login(String username, String password) {

        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "admin" ;
        }catch (Exception e){
            return "loginError" ;
        }
    }

    @GetMapping("/loginError")
    public String getIndex()  {
        log.info("loginError");
        return "loginError";
    }

    @GetMapping("otherPage")
    public String otherPage(HttpServletRequest request){
        log.info("other page");
        return "other page";
    }


}
