package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.IUserService;
import com.chongdong.lotterysurvey.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_user(用户表)】的数据库操作Service实现
* @createDate 2023-07-06 10:51:34
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Map addUserById(User user) {

        return null;
    }
}




