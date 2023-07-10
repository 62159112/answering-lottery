package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.LotteryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/raffle")
public class RaffleController {
    @Resource
    private LotteryService lotteryService;
    /**
     * 获取用户抽奖结果
     * */
    @GetMapping("/{userId}")
    public ResponseMap getResult(@PathVariable Integer userId){
        return lotteryService.getResult(userId);
    }
    /**
     * 获取最后一日奖池剩余
     * */
    @GetMapping("/last")
    public ResponseMap residueLottery() throws ParseException {
        return lotteryService.residueLottery();
    }
}
