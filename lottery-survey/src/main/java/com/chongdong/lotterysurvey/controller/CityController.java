package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.CityService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {
    @Resource
    private CityService cityService;

    @GetMapping
    public ResponseMap listCity(){
        return cityService.listCity();
    }

    @GetMapping("/123")
    public String test(){
        return "test";
    }
}
