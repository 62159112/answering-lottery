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
import java.time.LocalDate;
import java.util.ArrayList;
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
        Page<Grades> page = new Page<>(1, 50);
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
        Page<Grades> page = new Page<>(1, 50);
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
        Page<Grades> page = new Page<>(1, 30);
        Page<Grades> resultPage = gradesService.page(page, queryWrapper);
        // 返回查询结果
        return ResponseMap.ok().data(resultPage);
    }
    // TODO: 2023/7/7  刷新
    @PostMapping
    public ResponseMap flushed(Integer userId,Integer answerDay){
        // 设置注册时间
        User user = userService.getOne(new QueryWrapper<User>().eq("id", userId));
        QueryWrapper<AnswerResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.between("createTime", LocalDate.of(2023, 7, answerDay),LocalDate.of(2023, 7, answerDay+1));
        List<AnswerResult> answerResultList = answerResultService.list(queryWrapper);
        Map map = new HashMap();
        int i =0;
        for (AnswerResult answerResult:answerResultList) {
            if (gradesService.queryGradesExit(answerResult.getUserId(),answerResult.getAnswerScore(),answerResult.getAnswerTime(),answerResult.getCreateTime().getDayOfMonth())<1){
                Grades grades = new Grades();
                grades.setRegtime(user.getCreateDate());
                grades.setUsername(user.getUserName());
                grades.setRegion(user.getUserRegion());
                grades.setUserid(user.getId());
                grades.setSpendtime(answerResult.getAnswerTime());
                grades.setScore(answerResult.getAnswerScore());
                grades.setAnswerday(answerResult.getCreateTime().getDayOfMonth());
                boolean save = gradesService.save(grades);
                String saveKey = "save"+ i++;
                map.put(saveKey,save);
            }else {
                Grades grades = new Grades();
                grades.setRegtime(user.getCreateDate());
                grades.setUsername(user.getUserName());
                grades.setRegion(user.getUserRegion());
                grades.setUserid(user.getId());
                grades.setSpendtime(answerResult.getAnswerTime());
                grades.setScore(answerResult.getAnswerScore());
                grades.setAnswerday(answerResult.getCreateTime().getDayOfMonth());

                QueryWrapper<Grades> updateWrapper = new QueryWrapper<>();
                updateWrapper.eq("userId",grades.getUserid());
                updateWrapper.eq("score",grades.getScore());
                updateWrapper.eq("spendTime",grades.getSpendtime());
                updateWrapper.eq("answerDay",answerResult.getCreateTime().getDayOfMonth());
                boolean update = gradesService.update(grades, updateWrapper);
                String updateKey = "update"+ i++;
                map.put(updateKey,update);
            }
        }
        return ResponseMap.ok().data(map);

    }

}
