package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.Prize;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.UserService;
import com.chongdong.lotterysurvey.service.PrizeService;
import com.chongdong.lotterysurvey.mapper.PrizeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_prize(奖励表)】的数据库操作Service实现
* @createDate 2023-07-07 10:39:36
*/
@Service
public class IPrizeService extends ServiceImpl<PrizeMapper, Prize>
    implements PrizeService{

    private ResponseMap responseMap = MapFactory.createMap();
    @Resource
    private UserService userService;

    @Override
    public void userEmpty(Integer userId) {
        Prize prize = this.getOne(new QueryWrapper<Prize>().eq("userId", userId));
        if(prize==null){
            User user = userService.getById(userId);
            Prize prize1 = new Prize();
            prize1.setUserid(userId);
            prize1.setUserphone(user.getUserPhone());
            addPrize(prize1);
        }
    }

    @Override
    public void addPrize(Prize prize) {
        this.save(prize);
    }

    @Override
    public ResponseMap getPrize(Integer userId) {
        Prize prize = this.getOne(new QueryWrapper<Prize>().eq("userId", userId));
        if (prize!=null){
            responseMap.setFlag(true);
            responseMap.setData(prize);
            responseMap.setMessage("查询用户奖励成功！");
        }else {
            responseMap.setFlag(false);
            responseMap.setData("");
            responseMap.setMessage("查询用户奖励失败！");
        }
        return responseMap ;
    }

}




