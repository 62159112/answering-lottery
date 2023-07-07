package com.chongdong.lotterysurvey.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chongdong.lotterysurvey.model.AnswerResult;

/**
* @author cd
* @description 针对表【tcd_answerResult(答题结果表)】的数据库操作Mapper
* @createDate 2023-07-06 14:50:26
* @Entity com.chongdong.lotterysurvery.model.Answer
*/
public interface AnswerResultMapper extends BaseMapper<AnswerResult> {
    /**
     * 根据选择的用户id和第几次答题
     * @param userId 1 用户id
     * @param answerSequence 1 第几次答题
     * @return 花费的时间
     */
    String searchSpendTimeById(Integer userId,Integer answerSequence);
}




