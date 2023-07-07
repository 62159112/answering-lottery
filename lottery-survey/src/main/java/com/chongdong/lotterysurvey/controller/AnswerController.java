package com.chongdong.lotterysurvey.controller;


import cn.hutool.http.server.HttpServerRequest;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.IUserService;
import com.chongdong.lotterysurvey.service.TopicService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.net.HttpCookie;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/answerResults")
public class AnswerController {
    @Resource
    private AnswerResultService answerResultService;
    @Resource
    private TopicService topicService;
    @Resource
    private IUserService userService;

    /**
     * 随机抽取10个问题
     * @return
     */
    @GetMapping("/selectTopic")
    public ResponseMap selectTopic(HttpServletRequest request){

        List<Topic> topics = topicService.list();

        return topicService.queryTopic(request,topics);

    }

    /**
     * 添加成绩
     * @param answerResult
     * @return
     */
   @PostMapping("/save")
    public ResponseMap save(AnswerResult answerResult, HttpServletRequest request){
         request.getCookies();
       return answerResultService.add(answerResult,request);
   }

    /**
     * 答题总分
     * @param request
     * @return
     */
   @GetMapping("/selectTotalScore")
    public ResponseMap selectScore(HttpServletRequest request){

       return answerResultService.selectScore(request);
   }

    /**
     * 分享朋友圈添加答题次数
     * @param id
     * @return
     */
    @PutMapping("/updateUserNumber")
    public ResponseMap updateUserNumber(Integer id){
      return   userService.updateUserNumber(id);
    }



}
