package com.chongdong.lotterysurvey.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseMap queryGradesById(@PathVariable Integer id){
        Grades byId = gradesService.getById(id);
        return byId!=null?ResponseMap.ok().data(byId):ResponseMap.error();
    }

    // 总的每日个人成绩排行
    @GetMapping("/list")
    public ResponseMap queryAllGradesOrderByAscAnswerDay() {
        Page<Grades> gradesPage = gradesService.queryAllGradesOrderByAscAnswerDay();
        // 返回查询结果
        return ResponseMap.ok().data(gradesPage);
    }

    // 查具体某日个人成绩排行
    @GetMapping("/listAnswerDay")
    public ResponseMap queryAllByAnswerDayOrderByScore(Integer answerDay) {
        Page<Grades> gradesPage = gradesService.queryAllByAnswerDayOrderByScore(answerDay);
        // 返回查询结果
        return ResponseMap.ok().data(gradesPage);
    }

    // 查询个人所有最高分成绩
    @GetMapping("/listUserMaxScore")
    public ResponseMap queryAllMaxScoreByUsername(String username) {
        Page<Grades> resultPage = gradesService.queryAllMaxScoreByUsername(username);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }
    // 查询个人所有成绩
    @GetMapping("/listUserScore")
    public ResponseMap queryAllByUsername(String username) {
        Page<Grades> resultPage = gradesService.queryAllByUsername(username);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }
    @PostMapping
    public ResponseMap flushed(Integer userId,Integer answerDay){
        Map map = gradesService.flushed(userId,answerDay);
        return ResponseMap.ok().data(map);
    }
}
