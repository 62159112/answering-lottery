package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Team;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wo
* @description 针对表【tcd_team】的数据库操作Service
* @createDate 2023-07-06 13:01:56
*/
public interface TeamService extends IService<Team> {
    Integer queryTeamExit(Integer answerDay,Integer streetId);
    Integer queryStreetIdByTeamName(String teamName);

    List<Team> queryAllOrderByTeamNumber();
}
