package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.City;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.CityService;
import com.chongdong.lotterysurvey.mapper.CityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_city(市区表)】的数据库操作Service实现
* @createDate 2023-07-06 10:45:10
*/
@Service
public class ICityService extends ServiceImpl<CityMapper, City>
    implements CityService{

    private ResponseMap responseMap = MapFactory.createMap();
    @Override
    public ResponseMap listCity() {
        List<City> list = this.list();
        if (list!=null){
            responseMap.setFlag(true);
            responseMap.setData(list);
            responseMap.setMessage("查询城市成功！");
        }else {
            responseMap.setFlag(false);
            responseMap.setData("");
            responseMap.setMessage("查询城市失败！");
        }
        return responseMap ;
    }
}




