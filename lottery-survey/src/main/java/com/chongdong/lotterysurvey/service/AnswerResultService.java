package com.chongdong.lotterysurvey.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;

/**
* @author cd
* @description 针对表【tcd_answerResult(答题结果表)】的数据库操作Service
* @createDate 2023-07-06 14:50:26
*/
public interface AnswerResultService extends IService<AnswerResult> {



    ResponseMap add(AnswerResult answerResult);
    Integer searchSpendTimeById(Integer userId,Integer answerSequence);
}
