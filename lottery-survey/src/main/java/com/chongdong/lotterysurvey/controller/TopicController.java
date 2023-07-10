package com.chongdong.lotterysurvey.controller;


import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Topic;
import com.chongdong.lotterysurvey.service.TopicService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Resource
    private TopicService topicService;

    //添加
    @PostMapping("/save")
    public ResponseMap save(Topic topic){
      return   topicService.saveTopic(topic);

    }
    //修改
    @PutMapping("/update")
    public ResponseMap update(Topic topic){
      return   topicService.updateTopicById(topic);

    }

    //删除
    @DeleteMapping("/delete/{id}")
    public ResponseMap delete(
            @PathVariable Integer id
    ){
       return topicService.deleteTopic(id);
    }

    //查询
    @GetMapping("/select/{current}")
    public ResponseMap select(
            @PathVariable Integer current,
            @RequestParam(required = false) String TopicContent
    ){
      return   topicService.selectPage(current,TopicContent);
    }

    //根据id查
    @GetMapping("/selectById/{id}")
    public ResponseMap selectById(
            @PathVariable Integer id
    ){
      return   topicService.selectById(id);
    }

}
