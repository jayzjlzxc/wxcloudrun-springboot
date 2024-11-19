package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.dto.UserInfo;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.service.CounterService;
import com.tencent.wxcloudrun.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    final Logger logger = LoggerFactory.getLogger(LoginController.class);;
    @Autowired
    private LoginService loginService;
    @PostMapping(value = "/login")
    ApiResponse create(@RequestBody UserInfo userInfo) {
        logger.info("login post request");
        Boolean login = loginService.login(userInfo);
        if (login){
            return ApiResponse.ok("success");
        }else{
            return ApiResponse.error("帐号或密码错误");
        }
    }
}
