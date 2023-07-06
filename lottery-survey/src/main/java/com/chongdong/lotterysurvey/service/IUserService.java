package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_user(用户表)】的数据库操作Service
* @createDate 2023-07-06 10:51:34
*/
public interface IUserService extends IService<User> {
    Map addUserById(User user);
}
