package com.chongdong.lotterysurvey.controller;


import cn.hutool.http.server.HttpServerRequest;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.TopicService;
import com.chongdong.lotterysurvey.service.UserService;
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
    private UserService userService;

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
     * 传id
     * 随机抽取10个问题
     * @return
     */
    /*@GetMapping("/queryTopic/{userId}")
    public ResponseMap queryTopic(
            @PathVariable Integer userId
    ){

        List<Topic> topics = topicService.list();

        return topicService.selectTopic(userId,topics);

    }*/



    /**
     * 添加成绩
     * @param answerResult
     * @return
     */
   @PostMapping("/save")
    public ResponseMap save(AnswerResult answerResult, HttpServletRequest request){

       return answerResultService.add(answerResult,request);
   }


    /**
     * 传id
     * 添加成绩
     * @param answerResult
     * @return
     */
   /*@PostMapping("/save")
    public ResponseMap save(AnswerResult answerResult){

       return answerResultService.add(answerResult);
   }*/




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
     * 答题总分
     * @param
     * @return
     */
  /* @GetMapping("/selectTotalScore/{userId}")
    public ResponseMap selectScore(
            @PathVariable Integer userId
   ){

       return answerResultService.selectScore(userId);
   }*/





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
