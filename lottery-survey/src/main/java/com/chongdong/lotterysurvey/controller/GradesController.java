package com.chongdong.lotterysurvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Team;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.TeamService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private TeamService teamService;
//    测试
    @GetMapping("/{id}")
    public ResponseMap queryGradesById(@PathVariable Integer id){
        Grades byId = gradesService.getById(id);
        return byId!=null?ResponseMap.ok().data(byId):ResponseMap.error();
    }
    // 总的每日个人成绩排行
    @GetMapping("/list")
    public ResponseMap queryAllGradesOrderByAscAnswerDay() {
        List<Grades> gradesPage = gradesService.queryAllGradesOrderByAscAnswerDay();
        // 返回查询结果
        return ResponseMap.ok().data(gradesPage);
    }
    // 查具体某日个人成绩排行
    @GetMapping("/listAnswerDay")
    public ResponseMap queryAllByAnswerDayOrderByScore(Integer answerDay) {
        List<Grades> gradesPage = gradesService.queryAllByAnswerDayOrderByScore(answerDay);
        // 返回查询结果
        return ResponseMap.ok().data(gradesPage);
    }
    // 查询个人所有最高分成绩
    @GetMapping("/listUserMaxScore")
    public ResponseMap queryAllMaxScoreByUsername(HttpServletRequest request) {
        List<Grades> resultPage = gradesService.queryAllMaxScoreByUsername(request);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }
    // 查询个人所有成绩
    @GetMapping("/listUserScore")
    public ResponseMap queryAllByUsername(HttpServletRequest request) {
        List<Grades> resultPage = gradesService.queryAllByUsername(request);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }
    // 刷新个人答题数据
    @PostMapping
    public ResponseMap flushed(Integer userId,Integer answerDay){
        Map map = gradesService.flushed(userId,answerDay);
        Team team = new Team();
                    team.setAnswerday(answerDay);
                    team.setStreetid(teamService.queryStreetIdByTeamName(map.get("region").toString()));
                    ResponseMap add = gradesService.add(team);
                    map.put("更新地区数据",add);
        return ResponseMap.ok().data(map);
    }
}
