package com.chongdong.lotterysurvey.service.impl;

import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.TopicService;
import com.chongdong.lotterysurvey.mapper.TopicMapper;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* @author cd
* @description 针对表【tcd_topic(题目)】的数据库操作Service实现
* @createDate 2023-07-06 15:51:53
*/
@Service
public class ITopicService extends ServiceImpl<TopicMapper, Topic>
    implements TopicService{

    private ResponseMap responseMap = MapFactory.createMap();
    @Resource
    UserService userService;


    @Override
    public ResponseMap queryTopic(HttpServletRequest request, List<Topic> topics) {
        Cookie[] cookies = request.getCookies();
        Integer id = null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();

            if(name.equals("userId")){
                String value = cookie.getValue();
                id = Integer.parseInt(value);
            }
        }

        User user = userService.getById(id);
        if (user.getUserNumber() > 0){
            Collections.shuffle(topics);
            responseMap.setData(topics.subList(0, 10));
            responseMap.setFlag(true);
            responseMap.setMessage("抽取题目成功！");
            return responseMap;
        }
        responseMap.setData(null);
        responseMap.setFlag(false);
        responseMap.setMessage("答题次数已用完！");
        return responseMap;
    }




}




