package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
   @Resource
    private IUserService userService;

}
