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
import java.util.List;

/**
* @author wo
* @description 针对表【tcd_team】的数据库操作Service实现
* @createDate 2023-07-06 13:01:56
*/
@Service
public class ITeamService extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

    @Override
    public Integer queryTeamExit(Integer answerDay, Integer streetId) {
        return baseMapper.searchAllByAnswerdayAndStreetid(answerDay, streetId);
    }
    @Override
    public Integer queryStreetIdByTeamName(String teamName) {
        return baseMapper.searchStreetidByTeamname(teamName);
    }

    @Override
    public List<Team> queryAllOrderByTeamNumber() {
        return baseMapper.searchAllOrderByTeamNumber();
    }
}




