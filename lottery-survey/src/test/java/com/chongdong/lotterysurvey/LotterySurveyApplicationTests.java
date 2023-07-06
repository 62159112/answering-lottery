package com.chongdong.lotterysurvey;

import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.StreetService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;


@SpringBootTest
class LotterySurveyApplicationTests {
    @Resource
    StreetService streetService;
    @Resource
    GradesService gradesService;

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

}
