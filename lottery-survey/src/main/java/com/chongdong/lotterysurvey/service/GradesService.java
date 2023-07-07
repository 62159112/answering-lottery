package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.Grades;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wo
* @description 针对表【tcd_grades(成绩表（个人成绩）)】的数据库操作Service
* @createDate 2023-07-07 11:51:40
*/
public interface GradesService extends IService<Grades> {
    Integer queryTeamNumber(Integer answerDay,String region);
}
