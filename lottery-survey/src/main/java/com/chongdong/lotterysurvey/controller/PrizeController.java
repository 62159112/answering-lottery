package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.PrizeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prize")
public class PrizeController {
    @Resource
    private PrizeService prizeService;

    @GetMapping("{userId}")
    public ResponseMap getPrize(@PathVariable Integer userId){
        return prizeService.getPrize(userId);
    }

    @GetMapping(value = {"/list","/list/{page}/{size}"})
    public ResponseMap listPrize(@PathVariable(value = "page",required = false)Integer page,
                                 @PathVariable(value = "size",required = false)Integer size){
        return prizeService.listPrize(page,size);
    }

    @PutMapping("/{userId}")
    public ResponseMap payPrize(@PathVariable Integer userId){
        return prizeService.payPrize(userId);
    }
}
