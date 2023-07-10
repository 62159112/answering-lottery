package com.chongdong.lotterysurvey.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Team;
import com.chongdong.lotterysurvey.service.TeamService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description: 团队（地域/街道）排名
 * Author: wo
 * Created: 2023/7/6 11:44
 * Modified By: wo
 * Last Modified: 2023/7/6 11:44
 */
@RestController
@RequestMapping("/team")
public class TeamController {
    @Resource
    TeamService teamService;
//    测试
    @GetMapping("/{id}")
    public ResponseMap queryTeamById(@PathVariable Integer id){
        Team byId = teamService.getById(id);
        return byId!=null?ResponseMap.ok().data(byId):ResponseMap.error();
    }
    // 地域/街道总排行榜（答题人次）
    @GetMapping("/list")
    public ResponseMap queryAllOrderByTeamNumber(){

        List<Team> teamNumber = teamService.queryAllOrderByTeamNumber();
        return teamNumber!=null?ResponseMap.ok().data(teamNumber):ResponseMap.error();
    }

    // 地域/街道每日排行榜（答题人次）-->传具体是哪号 例：2023年09月06日==>传 6 就行啦
    @GetMapping("/listAnswerDay")
    public ResponseMap queryAllByAnswerDayOrderByTeamNumber(Integer answerDay){
        List<Team> teamNumber = teamService.list(new QueryWrapper<Team>().eq("answerDay",answerDay).orderByDesc("teamNumber"));
        return teamNumber!=null?ResponseMap.ok().data(teamNumber):ResponseMap.error();
    }
}
