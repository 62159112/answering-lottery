package com.chongdong.lotterysurvey.mapper;

import com.chongdong.lotterysurvey.model.Grades;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wo
* @description 针对表【tcd_grades(成绩表（个人成绩）)】的数据库操作Mapper
* @createDate 2023-07-06 13:01:56
* @Entity com.chongdong.lotterysurvey.model.Grades
*/
public interface GradesMapper extends BaseMapper<Grades> {
    /**
     * 查询某日某地区所有答题人数（人次）-->无论及不及格都计入
     * @param answerDay 答题日期（几号）
     * @param region    团队（地域/街道）
     * @return 答题次数
     */
    Integer searchAllByAnswerDay(Integer answerDay,String region);
}




