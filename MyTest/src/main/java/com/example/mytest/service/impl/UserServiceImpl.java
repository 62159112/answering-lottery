package com.example.mytest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mytest.model.User;
import com.example.mytest.service.UserService;
import com.example.mytest.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* @author cd
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-07-18 15:22:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    private Map<String, String> userMap = new HashMap<>();

    @Override
    public String login(String username, String password) {
        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        if (user!=null){
            if (user.getPassword().equals(password)){
                userMap.put(username,password);
                // 生成一个token并存储到用户表中
                String token = UUID.randomUUID().toString();
                userMap.put(username, token);
                return token;

            }
        }
        // 检查用户名和密码是否正确
        return null;
    }

    public boolean authenticate(String username, String token) {
        // 检查token是否有效
        return userMap.containsKey(username) && userMap.get(username).equals(token);
    }
}




