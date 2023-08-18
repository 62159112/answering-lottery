package com.example.mytest.service;

import com.example.mytest.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cd
* @description 针对表【user】的数据库操作Service
* @createDate 2023-07-18 15:22:56
*/
public interface UserService extends IService<User> {
    String login(String username, String password);

}
