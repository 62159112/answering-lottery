package com.chongdong.lotterysurvey.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author cd
* @description 针对表【tcd_answerResult(答题结果表)】的数据库操作Service
* @createDate 2023-07-06 14:50:26
*/
public interface AnswerResultService extends IService<AnswerResult> {


//添加答题成绩
    ResponseMap add(AnswerResult answerResult,HttpServletRequest request);
    //添加答题成绩根据
    ResponseMap add(AnswerResult answerResult);

    ResponseMap selectScore(HttpServletRequest request);

    ResponseMap selectScore(Integer id);

}
