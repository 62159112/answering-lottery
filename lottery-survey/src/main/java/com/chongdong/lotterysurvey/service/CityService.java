package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.City;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chongdong.lotterysurvey.model.ResponseMap;

/**
* @author cd
* @description 针对表【tcd_city(市区表)】的数据库操作Service
* @createDate 2023-07-06 10:45:10
*/
public interface CityService extends IService<City> {
    ResponseMap listCity();
}
