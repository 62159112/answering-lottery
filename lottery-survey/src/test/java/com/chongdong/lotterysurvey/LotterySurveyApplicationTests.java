package com.chongdong.lotterysurvey;

import com.chongdong.lotterysurvey.service.StreetService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LotterySurveyApplicationTests {
    @Resource
    StreetService streetService;
    @Test
    void contextLoads() {
        String queryStreetFullName = streetService.queryStreetFullName(9);
        System.out.println(queryStreetFullName);
    }

}
