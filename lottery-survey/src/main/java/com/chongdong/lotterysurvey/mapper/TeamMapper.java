package com.chongdong.lotterysurvey.mapper;

import com.chongdong.lotterysurvey.model.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wo
* @description 针对表【tcd_team】的数据库操作Mapper
* @createDate 2023-07-06 13:01:56
* @Entity com.chongdong.lotterysurvey.model.Team
*/
public interface TeamMapper extends BaseMapper<Team> {

    Integer searchAllByAnswerdayAndStreetid(Integer answerDay,String teamName);
}




