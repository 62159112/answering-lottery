package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.LotteryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffle")
public class RaffleController {
    @Resource
    private LotteryService lotteryService;

    @GetMapping("/{userId}")
    public ResponseMap getResult(@PathVariable Integer userId){
        return null;
    }
}