package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.StreetService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/street")
public class StreetController {
    @Resource
    private StreetService streetService;
    /**
     * 获取区域全部街道信息
     * */
    @GetMapping("/{cityId}")
    public ResponseMap listStreetByCity(@PathVariable String cityId){
        return streetService.listStreetByCity(cityId);
    }

}
