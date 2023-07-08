package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Team;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.StreetService;
import com.chongdong.lotterysurvey.service.TeamService;
import com.chongdong.lotterysurvey.mapper.TeamMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
* @author wo
* @description 针对表【tcd_team】的数据库操作Service实现
* @createDate 2023-07-06 13:01:56
*/
@Service
public class ITeamService extends ServiceImpl<TeamMapper, Team>
    implements TeamService{
    @Resource
    StreetService streetService;
    @Resource
    GradesService gradesService;
    @Override
    public Integer queryTeamExit(Integer answerDay, Integer streetId) {
        return baseMapper.searchAllByAnswerdayAndStreetid(answerDay, streetId);
    }
    @Override
    public ResponseMap add(Team team) {
        if (this.queryTeamExit(team.getAnswerday(),team.getStreetid())<1){
            // 添加团队
            String streetFullName = streetService.queryStreetFullName(team.getStreetid());
            // 设置答题日期（几号）
            team.setTeamname(streetFullName);
            // team.setAnswerday(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            // 设置答题人次
            team.setTeamnumber(gradesService.queryTeamNumber(team.getAnswerday(), streetFullName));
            boolean save = this.save(team);
            return save?ResponseMap.ok().message("添加成功").data(gradesService.queryTeamNumber(team.getAnswerday(), streetFullName)) :ResponseMap.error().message("添加失败");
        }else {
            // 更新团队
            String streetFullName = streetService.queryStreetFullName(team.getStreetid());
            // 设置答题日期（几号）
            team.setTeamname(streetFullName);
            // team.setAnswerday(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            // 设置答题人次
            System.out.println(gradesService.queryTeamNumber(team.getAnswerday(), streetFullName));
            team.setTeamnumber(gradesService.queryTeamNumber(team.getAnswerday(), streetFullName));
            QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("answerDay",team.getAnswerday());
            queryWrapper.eq("teamName",streetFullName);
            queryWrapper.eq("streetId",team.getStreetid());
            boolean update = this.update(team, queryWrapper);
            return update?ResponseMap.ok().message("更新成功").data(gradesService.queryTeamNumber(team.getAnswerday(), streetFullName)) :ResponseMap.error().message("更新失败");
        }
    }

    @Override
    public Integer queryStreetIdByTeamName(String teamName) {
        return baseMapper.searchStreetidByTeamname(teamName);
    }
}




