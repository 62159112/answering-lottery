package com.chongdong.lotterysurvey;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.StreetService;
import com.chongdong.lotterysurvey.service.TeamService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


@SpringBootTest
class LotterySurveyApplicationTests {
    @Resource
    StreetService streetService;
    @Resource
    GradesService gradesService;
    @Resource
    TeamService teamService;
    @Resource
    AnswerResultService answerResultService;

    @Test
    void contextLoads() {
        String queryStreetFullName = streetService.queryStreetFullName(9);
        System.out.println(queryStreetFullName);
        System.out.println(Calendar. getInstance(). get (Calendar. DAY_OF_MONTH));
    }
    @Test
    void test1(){
        Integer teamNumber = gradesService.queryTeamNumber(1, "红花岗区老城街道");
        System.out.println(teamNumber);
    }
    @Test
    void test2(){
        Integer teamNumber = teamService.queryTeamExit(6, "红花岗区老城街道");
        System.out.println(teamNumber);
    }
    @Test
    void test3(){
        AnswerResult answerResult = answerResultService.getById(1);
        long time = answerResultService.searchSpendTimeById(1);
        System.out.println(time);

        QueryWrapper<AnswerResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", 1);
        queryWrapper.eq("answerSequence",2);
        AnswerResult answerResul = answerResultService.getOne(queryWrapper);
        System.out.println(answerResul.toString());
        System.out.println(answerResul.getCreateTime());
    }

}
