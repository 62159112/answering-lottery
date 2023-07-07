package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.Prize;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chongdong.lotterysurvey.model.ResponseMap;

/**
* @author cd
* @description 针对表【tcd_prize(奖励表)】的数据库操作Service
* @createDate 2023-07-07 11:06:16
*/
public interface PrizeService extends IService<Prize> {
    void userEmpty(Integer userId);

    void addPrize(Prize prize);

    ResponseMap getPrize(Integer userId);

}
