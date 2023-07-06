package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.Lottery;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.LotteryService;
import com.chongdong.lotterysurvey.mapper.LotteryMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_lottery】的数据库操作Service实现
* @createDate 2023-07-06 11:30:52
*/
@Service
public class ILotteryService extends ServiceImpl<LotteryMapper, Lottery>
    implements LotteryService{

    ResponseMap responseMap = MapFactory.createMap();
    @Override
    public ResponseMap getResult(Integer userId) {
        return null;
    }
}




