package com.chongdong.lotterysurvey.controller;


import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.TopicService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answerResults")
public class AnswerController {
    @Resource
    private AnswerResultService answerResultService;
    @Resource
    private TopicService topicService;

    @GetMapping("/selectTopic")
    public ResponseMap selectTopic(){

        List<Topic> topics = topicService.list();

        return topicService.queryTopic(topics);

    }

   @PostMapping("/save")
    public ResponseMap save(@RequestParam AnswerResult answerResult){
       answerResultService.add(answerResult);

       return null;
   }

}
