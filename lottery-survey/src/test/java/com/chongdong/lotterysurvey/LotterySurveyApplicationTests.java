package com.chongdong.lotterysurvey;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.StreetService;
import com.chongdong.lotterysurvey.service.TeamService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
        String queryStreetFullName = streetService.queryStreetFullName(200);
        System.out.println(queryStreetFullName);
        System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
    @Test
    void test1(){
        Integer teamNumber = gradesService.queryTeamNumber(1, "红花岗区老城街道");
        System.out.println(teamNumber);
    }
    @Test
    void test2(){
        Integer teamNumber = teamService.queryTeamExit(6, 1);
        System.out.println(teamNumber);
    }
    @Test
    void test3(){

        QueryWrapper<AnswerResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", 2);
        queryWrapper.between("createTime",LocalDate.now(),LocalDate.now().plusDays(1));
        List<AnswerResult> list = answerResultService.list(queryWrapper);

        for (AnswerResult answerResult:list) {
            System.out.println(answerResult.getCreateTime());
        }
    }
    @Test
    void contextLoadsA() {
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime", "MIN(regtime) as regtime","region","answerDay")
                .groupBy("userid", "username","region", "answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .orderByAsc("regtime");

        // 查询前十名成绩
        Page<Grades> page = new Page<>(1, 20);
        Page<Grades> resultPage = gradesService.page(page, queryWrapper);
        System.out.println(resultPage);
    }
    @Test
    void contextLoadsB(){
        //当前时间
        Date date = DateUtil.date();
//当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
//当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
//当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
//当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();
        System.out.println(date+"\n"+date2+"\n"+date3+"\n"+today);

    }

    @Test
    void contextLoadsC(){
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        System.out.println(today);
        System.out.println(tomorrow);
    }
    @Test
    void contextLoadsD(){
        Integer integer = gradesService.queryGradesExit(2, 20, 60, 8);
        System.out.println(integer);
    }
    @Test
    void contextLoadsE(){
        Integer integer = teamService.queryStreetIdByTeamName("桐梓县尧龙山镇");
        System.out.println(integer);
    }
}
