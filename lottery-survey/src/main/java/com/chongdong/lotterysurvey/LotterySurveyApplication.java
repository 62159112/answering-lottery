package com.chongdong.lotterysurvey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chongdong.lotterysurvey.mapper")
public class LotterySurveyApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotterySurveyApplication.class, args);
    }
}
