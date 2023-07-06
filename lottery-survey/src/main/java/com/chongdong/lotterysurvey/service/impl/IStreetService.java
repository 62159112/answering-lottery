package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.Street;
import com.chongdong.lotterysurvey.service.StreetService;
import com.chongdong.lotterysurvey.mapper.StreetMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_street(镇/街道表)】的数据库操作Service实现
* @createDate 2023-07-06 10:54:07
*/
@Service
public class IStreetService extends ServiceImpl<StreetMapper, Street>
    implements StreetService{

    private ResponseMap responseMap = MapFactory.createMap();
    @Override
    public ResponseMap listStreetByCity(Integer id) {
        QueryWrapper<Street> queryWrapper = new QueryWrapper<>();
        List<Street> streetList = this.list(queryWrapper.eq("cityId", id));
        if (streetList!=null){
            responseMap.setFlag(true);
            responseMap.setData(streetList);
            responseMap.setMessage("查询城市街道成功！");
        }else {
            responseMap.setFlag(false);
            responseMap.setData("");
            responseMap.setMessage("查询城市街道失败！传入参数不对！");
        }
        return responseMap ;
    }
}




