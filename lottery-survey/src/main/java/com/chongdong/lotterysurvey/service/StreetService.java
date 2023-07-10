package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Street;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cd
* @description 针对表【tcd_street(镇/街道表)】的数据库操作Service
* @createDate 2023-07-10 15:34:32
*/
public interface StreetService extends IService<Street> {
    ResponseMap listStreetByCity(String cityId);

}
