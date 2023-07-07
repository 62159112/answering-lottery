package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.mapper.GradesMapper;
import org.springframework.stereotype.Service;

/**
* @author wo
* @description 针对表【tcd_grades(成绩表（个人成绩）)】的数据库操作Service实现
* @createDate 2023-07-07 11:51:40
*/
@Service
public class IGradesService extends ServiceImpl<GradesMapper, Grades>
    implements GradesService{
    @Override
    public Integer queryTeamNumber(Integer answerDay, String region) {
        return baseMapper.searchAllByAnswerDay(answerDay,region);
    }
}




