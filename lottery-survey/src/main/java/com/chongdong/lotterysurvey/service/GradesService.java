package com.chongdong.lotterysurvey.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongdong.lotterysurvey.model.Grades;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
* @author wo
* @description 针对表【tcd_grades(成绩表（个人成绩）)】的数据库操作Service
* @createDate 2023-07-07 11:51:40
*/
public interface GradesService extends IService<Grades> {
    Integer queryTeamNumber(Integer answerDay,String region);

    Integer queryGradesExit(Integer userId,Integer score,Integer spendTime,Integer answerDay);

    Page<Grades> queryAllGradesOrderByAscAnswerDay();

    Page<Grades> queryAllByAnswerDayOrderByScore(Integer answerDay);

    Page<Grades> queryAllMaxScoreByUsername(HttpServletRequest request);

    Page<Grades> queryAllByUsername(HttpServletRequest request);

    Map flushed(Integer userId, Integer answerDay);
}
