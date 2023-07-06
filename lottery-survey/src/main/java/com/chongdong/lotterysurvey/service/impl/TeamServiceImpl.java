package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.Team;
import com.chongdong.lotterysurvey.service.TeamService;
import com.chongdong.lotterysurvey.mapper.TeamMapper;
import org.springframework.stereotype.Service;

/**
* @author wo
* @description 针对表【tcd_team】的数据库操作Service实现
* @createDate 2023-07-06 10:37:43
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

}




