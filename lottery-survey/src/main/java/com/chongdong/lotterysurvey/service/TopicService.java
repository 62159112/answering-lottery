package com.chongdong.lotterysurvey.service;

import cn.hutool.http.server.HttpServerRequest;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_topic(题目)】的数据库操作Service
* @createDate 2023-07-06 15:51:53
*/
public interface TopicService extends IService<Topic> {

    ResponseMap queryTopic(HttpServletRequest request, List<Topic> topics);


    ResponseMap selectTopic(Integer userId, List<Topic> topics);
    
    

    ResponseMap saveTopic(Topic topic);
    
    ResponseMap updateTopicById(Topic topic);

    ResponseMap deleteTopic(Integer id);

    ResponseMap selectPage(Integer current, String topicContent);

    ResponseMap selectById(Integer id);
}
