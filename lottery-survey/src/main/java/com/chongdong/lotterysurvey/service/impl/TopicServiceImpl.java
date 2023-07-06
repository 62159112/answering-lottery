package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.chongdong.lotterysurvey.service.TopicService;
import com.chongdong.lotterysurvey.mapper.TopicMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* @author cd
* @description 针对表【tcd_topic(题目)】的数据库操作Service实现
* @createDate 2023-07-06 15:51:53
*/
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic>
    implements TopicService{

    private ResponseMap responseMap = MapFactory.createMap();

    @Resource
    private TopicMapper topicMapper;

    @Override
    public ResponseMap queryTopic(List<Topic> list) {
        Collections.shuffle(list);
        responseMap.setData(list.subList(0, 10));
        responseMap.setFlag(true);
        responseMap.setMessage("抽取题目成功！");
        return responseMap;
    }




}




