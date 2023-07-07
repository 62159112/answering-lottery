package com.chongdong.lotterysurvey.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Team;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.StreetService;
import com.chongdong.lotterysurvey.service.TeamService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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
    @Resource
    StreetService streetService;
    @Resource
    GradesService gradesService;
    @GetMapping("/{id}")
    public ResponseMap queryTeamById(@PathVariable Integer id){
        Team byId = teamService.getById(id);
        return byId!=null?ResponseMap.ok().data(byId):ResponseMap.error();
    }
    // 地域/街道总排行榜（答题人次）
    @GetMapping("/list")
    public ResponseMap queryAllOrderByTeamNumber(){
        List<Team> teamNumber = teamService.list(new QueryWrapper<Team>().orderByDesc("teamNumber"));
        return teamNumber!=null?ResponseMap.ok().data(teamNumber):ResponseMap.error();
    }

    // 地域/街道每日排行榜（答题人次）-->传具体是哪号 例：2023年09月06日==>传 6 就行啦
    @GetMapping("/listAnswerDay")
    public ResponseMap queryAllByAnswerDayOrderByTeamNumber(Integer answerDay){
        List<Team> teamNumber = teamService.list(new QueryWrapper<Team>().eq("answerDay",answerDay).orderByDesc("teamNumber"));
        return teamNumber!=null?ResponseMap.ok().data(teamNumber):ResponseMap.error();
    }

    /**
     * 刷新团队答题人次
     * @return 团队（地域/街道答题）信息刷新
     */
    @PostMapping
    public ResponseMap add(Team team){
        if (teamService.queryTeamExit(team.getAnswerday(),team.getTeamname())<1){
            // 添加团队
            String streetFullName = streetService.queryStreetFullName(team.getStreetid());
            // 设置答题日期（几号）
            team.setAnswerday(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            // 设置答题人次
            team.setTeamnumber(gradesService.queryTeamNumber(team.getAnswerday(), streetFullName));
            boolean save = teamService.save(team);
            return save?ResponseMap.ok().message("添加成功") :ResponseMap.error().message("添加失败");
        }else {
            // 更新团队
            String streetFullName = streetService.queryStreetFullName(team.getStreetid());
            // 设置答题日期（几号）
            team.setAnswerday(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            // 设置答题人次
            team.setTeamnumber(gradesService.queryTeamNumber(team.getAnswerday(), streetFullName));
            QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("answerDay",team.getAnswerday());
            queryWrapper.eq("teamName",team.getTeamname());
            queryWrapper.eq("streetId",team.getStreetid());
            boolean update = teamService.update(team, queryWrapper);
            return update?ResponseMap.ok().message("更新成功") :ResponseMap.error().message("更新失败");
        }
    }

}
