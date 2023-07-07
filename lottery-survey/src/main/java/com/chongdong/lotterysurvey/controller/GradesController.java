package com.chongdong.lotterysurvey.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

/**
 * Description: 个人成绩（答题成绩）
 * Author: wo
 * Created: 2023/7/6 10:43
 * Modified By: wo
 * Last Modified: 2023/7/6 10:43
 */
@RestController
@RequestMapping("/grades")
public class GradesController {
    @Resource
    private GradesService gradesService;
    @Resource
    UserService userService;
    @Resource
    AnswerResultService answerResultService;
    @GetMapping("/{id}")
    public ResponseMap queryGradesById(@PathVariable Integer id){
        Grades byId = gradesService.getById(id);
        return byId!=null?ResponseMap.ok().data(byId):ResponseMap.error();
    }

    // 总的每日个人成绩排行
    @GetMapping("/list")
    public ResponseMap queryAllGradesOrderByAscAnswerDay() {
        // 查询条件，根据总分、答题用时和注册日期进行排序
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
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }

    // 查具体某日个人成绩排行
    @GetMapping("/listAnswerDay")
    public ResponseMap queryAllByAnswerDayOrderByScore(Integer answerDay) {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime", "MIN(regtime) as regtime","region","answerDay")
                .groupBy("userid", "username","region","answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .orderByAsc("regtime")
                .eq("answerDay",answerDay);
        // 查询前十名成绩
        Page<Grades> page = new Page<>(1, 10);
        IPage<Grades> resultPage = gradesService.page(page, queryWrapper);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }

    // 查询个人所有最高分成绩
    @GetMapping("/listUserMaxScore")
    public ResponseMap queryAllMaxScoreByUsername(String username) {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime","region","answerDay")
                .groupBy("userid", "username","region","answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .eq("username",username);

        // 查询前十条成绩
        Page<Grades> page = new Page<>(1, 10);
        Page<Grades> resultPage = gradesService.page(page, queryWrapper);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }
    // 查询个人所有成绩
    @GetMapping("/listUserScore")
    public ResponseMap queryAllByUsername(String username) {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .eq("username",username);
        // 查询前十条成绩
        Page<Grades> page = new Page<>(1, 10);
        Page<Grades> resultPage = gradesService.page(page, queryWrapper);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }
    // 刷新
    @PostMapping
    public ResponseMap flushed(){
        // 设置注册时间
        Grades grades = new Grades();
        User user = userService.getOne(new QueryWrapper<User>().eq("id", grades.getUserid()));
        QueryWrapper<AnswerResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", grades.getUserid()).eq("answerSequence",1);
        AnswerResult answerResult = answerResultService.getOne(queryWrapper);
        grades.setRegtime(user.getCreateDate());
        grades.setUsername(user.getUserName());
        grades.setRegion(user.getUserRegion());
        grades.setSpendtime(answerResultService.searchSpendTimeById(1));
        grades.setScore(answerResult.getAnswerScore());
        grades.setAnswerday(1);
        boolean save = gradesService.save(grades);
        return save?ResponseMap.ok():ResponseMap.error();
    }

}
