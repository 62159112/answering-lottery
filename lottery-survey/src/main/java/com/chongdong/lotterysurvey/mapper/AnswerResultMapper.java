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
     * 根据选择的答题id查询答题时间
     * @param id 1 老城街道
     * @return 红花岗区老城街道
     */
    String searchSpendTimeById(Integer id);
}




