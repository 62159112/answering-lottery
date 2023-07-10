package com.chongdong.lotterysurvey.service.impl;

import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.Collection;
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

    @Resource
    TopicMapper topicMapper;

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

    @Override
    public ResponseMap selectTopic(Integer userId, List<Topic> topics) {
        User user = userService.getById(userId);
        if (user.getUserNumber() > 0){

            //修改答题次数
           // user.setUserNumber(user.getUserNumber()-1);
           // final boolean bool = userService.updateById(user);
            //if (bool){
                Collections.shuffle(topics);
                responseMap.setData(topics.subList(0, 10));
                responseMap.setFlag(true);
                responseMap.setMessage("抽取题目成功！");
                return responseMap;
           // }
        }
        responseMap.setData(null);
        responseMap.setFlag(false);
        responseMap.setMessage("答题次数已用完！");
        return responseMap;
    }

    @Override
    public ResponseMap saveTopic(Topic topic) {
         int insert = topicMapper.insert(topic);
         if (insert>=1){
             responseMap.setData(insert);
             responseMap.setFlag(true);
             responseMap.setMessage("添加题库成功！");
         }else {
             responseMap.setData(null);
             responseMap.setFlag(false);
             responseMap.setMessage("添加题库失败！");
         }

        return responseMap;
    }

    @Override
    public ResponseMap updateTopicById(Topic topic) {
        final int update = topicMapper.updateById(topic);
        if (update>=1){
            responseMap.setData(update);
            responseMap.setFlag(true);
            responseMap.setMessage("修改题库成功！");
        }else {
            responseMap.setData(null);
            responseMap.setFlag(false);
            responseMap.setMessage("修改题库失败！");
        }


        return responseMap;
    }

    @Override
    public ResponseMap deleteTopic(Integer id) {
        final int delete = topicMapper.deleteById(id);
        if (delete>=1){
            responseMap.setData(delete);
            responseMap.setFlag(true);
            responseMap.setMessage("删除题库成功！");
        }else {
            responseMap.setData(null);
            responseMap.setFlag(false);
            responseMap.setMessage("删除题库成功！");
        }


        return responseMap;
    }

    @Override
    public ResponseMap selectPage(Integer current, String topicContent) {
         QueryWrapper<Topic> wrapper = new QueryWrapper<>();
         Page<Topic> page = new Page<>(current,10);
         if (topicContent != null&& !topicContent.isEmpty()){
             wrapper.like("demandName",topicContent);
                    // .select().orderByDesc("createTime");
         }else {
            // wrapper.select().orderByDesc("createTime");
         }
        Page<Topic> topicPage = topicMapper.selectPage(page, wrapper);
         if (topicPage != null){
             responseMap.setData(topicPage);
             responseMap.setFlag(true);
             responseMap.setMessage("查询成功！");
         }else {
             responseMap.setData(null);
             responseMap.setFlag(false);
             responseMap.setMessage("查询失败！");
         }
        return responseMap;
    }

    @Override
    public ResponseMap selectById(Integer id) {
        Topic topic = topicMapper.selectById(id);
        if (topic != null){
            responseMap.setData(topic);
            responseMap.setFlag(true);
            responseMap.setMessage("查询成功！");
        }else {
            responseMap.setData(null);
            responseMap.setFlag(true);
            responseMap.setMessage("查询失败！");
        }
        return responseMap;
    }

}




