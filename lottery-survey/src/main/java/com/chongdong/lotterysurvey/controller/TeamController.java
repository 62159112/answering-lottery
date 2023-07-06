package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Team;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.TeamService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private TeamService teamService;
    @GetMapping("/{id}")
    public ResponseMap queryTeamById(@PathVariable Integer id){
        Team byId = teamService.getById(id);
        ResponseMap responseMap = new ResponseMap();
        responseMap.setData(byId);
        responseMap.setFlag(true);
        responseMap.setMessage("成功");
        return responseMap;
    }
}
