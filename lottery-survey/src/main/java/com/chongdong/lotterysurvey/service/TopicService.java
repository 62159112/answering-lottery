package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_topic(题目)】的数据库操作Service
* @createDate 2023-07-06 15:51:53
*/
public interface TopicService extends IService<Topic> {

    ResponseMap queryTopic(List<Topic> list);



}
