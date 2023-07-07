package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.Lottery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chongdong.lotterysurvey.model.ResponseMap;

import java.text.ParseException;

/**
* @author cd
* @description 针对表【tcd_lottery】的数据库操作Service
* @createDate 2023-07-06 11:30:52
*/
public interface LotteryService extends IService<Lottery> {
    ResponseMap getResult(Integer userId);

    ResponseMap getLottery();

    Boolean addLottery();

    Boolean addLotteryFirst();

    ResponseMap residueLottery() throws ParseException;
}
