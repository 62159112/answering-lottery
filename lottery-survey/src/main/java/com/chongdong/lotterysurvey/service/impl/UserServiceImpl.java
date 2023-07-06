package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.UserService;
import com.chongdong.lotterysurvey.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_user(用户表)】的数据库操作Service实现
* @createDate 2023-07-06 10:51:34
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




