package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.Lottery;
import com.chongdong.lotterysurvey.model.Prize;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.LotteryService;
import com.chongdong.lotterysurvey.mapper.LotteryMapper;
import com.chongdong.lotterysurvey.service.PrizeService;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
* @author cd
* @description 针对表【tcd_lottery】的数据库操作Service实现
* @createDate 2023-07-06 11:30:52
*/
@Service
public class ILotteryService extends ServiceImpl<LotteryMapper, Lottery>
    implements LotteryService{

    ResponseMap responseMap = MapFactory.createMap();

    private final DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private UserService userService;

    @Resource
    private PrizeService prizeService;
    /**
     * 传入userid获取该用户是否还有抽奖次数
     * 若有次数获取抽奖结果
     * */
    @Override
    public ResponseMap getResult(Integer userId) {
        User user = userService.getById(userId);
        prizeService.userEmpty(userId);
        Prize prize = prizeService.getOne(new QueryWrapper<Prize>().eq("userId", userId));
        ResponseMap resultMap = getLottery();
        if (user.getUserDrawNumber()>0){
            if (resultMap.getFlag()){
                Lottery lottery = (Lottery) resultMap.getData();
                Random random = new Random();
                int num = random.nextInt(100)+1;
                if (num>90){
                    if (lottery.getPrizethree()!=0){
                        responseMap.setFlag(true);
                        responseMap.setData("恭喜抽中一等奖！");
                        responseMap.setMessage("抽奖成功!");
                        prize.setPrize(prize.getPrize().add(new BigDecimal(2)));
                        prizeService.updateById(prize);
                        lottery.setPrizethree(lottery.getPrizethree()-1);
                        saveOrUpdate(lottery);
                    }else {
                        responseMap.setFlag(true);
                        responseMap.setData("谢谢参与！");
                        responseMap.setMessage("抽奖成功!");
                    }
                }else if (num>70){
                    if(lottery.getPrizetwo()!=0){
                        responseMap.setFlag(true);
                        responseMap.setData("恭喜抽中二等奖！");
                        responseMap.setMessage("抽奖成功!");
                        prize.setPrize(prize.getPrize().add(new BigDecimal(1)));
                        prizeService.updateById(prize);
                        lottery.setPrizetwo(lottery.getPrizetwo()-1);
                        saveOrUpdate(lottery);
                    }else {
                        responseMap.setFlag(true);
                        responseMap.setData("谢谢参与！");
                        responseMap.setMessage("抽奖成功!");
                    }
                }else if(num>20){
                    if(lottery.getPrizeone()!=0){
                        responseMap.setFlag(true);
                        responseMap.setData("恭喜抽中三等奖！");
                        responseMap.setMessage("抽奖成功!");
                        prize.setPrize(prize.getPrize().add(new BigDecimal("0.3")));
                        prizeService.updateById(prize);
                        lottery.setPrizeone(lottery.getPrizeone()-1);
                        saveOrUpdate(lottery);
                    }else {
                        responseMap.setFlag(true);
                        responseMap.setData("谢谢参与！");
                        responseMap.setMessage("抽奖成功!");
                    }
                }else {
                    responseMap.setFlag(true);
                    responseMap.setData("谢谢参与！");
                    responseMap.setMessage("抽奖成功!");
                }
                user.setUserDrawNumber(user.getUserDrawNumber()-1);
                userService.saveOrUpdate(user);
            }
        }else {
            responseMap.setFlag(false);
            responseMap.setData("用户无抽奖次数或该用户不存在，请答题获取抽奖次数！");
            responseMap.setMessage("抽奖失败!");
        }
        return responseMap;
    }
    /**
     * 获取当前奖池
     * */
    @Override
    public ResponseMap getLottery() {
        QueryWrapper<Lottery> queryWrapper = new QueryWrapper<>();
        Lottery lottery = this.getOne(queryWrapper.eq("activityTime", simpleDateFormat.format(new Date())));
        if (lottery!=null){
            responseMap.setFlag(true);
            responseMap.setData(lottery);
            responseMap.setMessage("查询当前剩余奖励成功");
        }else{
            responseMap.setFlag(false);
            responseMap.setData("");
            responseMap.setMessage("活动已过期");
        }
        return responseMap;
    }
    /**
     * 添加奖池(定时任务使用
     * */
    @Override
    public void addLottery() {
        QueryWrapper<Lottery> queryWrapper = new QueryWrapper<>();
        Lottery lottery = this.getOne(queryWrapper.eq("activityTime", simpleDateFormat.format(new Date())));
        lottery.setPrizeone(lottery.getPrizeone()+200);
        lottery.setPrizetwo(lottery.getPrizetwo()+20);
        lottery.setPrizethree(lottery.getPrizethree()+10);
    }
    /**
     * 将昨日剩余奖池添加进今日奖池(定时任务使用
     * */
    @Override
    public void addLotteryFirst() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        Date yesterday = calendar.getTime();
        Lottery yesterdayLottery = this.getOne(new QueryWrapper<Lottery>().eq("activityTime", simpleDateFormat.format(yesterday)));
        Lottery lottery = this.getOne(new QueryWrapper<Lottery>().eq("activityTime", simpleDateFormat.format(new Date())));
        if (yesterdayLottery!=null){
            if (yesterdayLottery.getPrizeone()!=0){
                lottery.setPrizeone(yesterdayLottery.getPrizeone());
                yesterdayLottery.setPrizeone(0);
            }
            if (yesterdayLottery.getPrizetwo()!=0){
                lottery.setPrizetwo(yesterdayLottery.getPrizetwo());
                yesterdayLottery.setPrizetwo(0);
            }
            if (yesterdayLottery.getPrizethree()!=0){
                lottery.setPrizethree(yesterdayLottery.getPrizethree());
                yesterdayLottery.setPrizethree(0);
            }
            saveOrUpdate(yesterdayLottery);
            saveOrUpdate(lottery);
        }
    }
    /**
     * 查询最后一日剩余奖池
     * */
    @Override
    public ResponseMap residueLottery() throws ParseException {
        Date lastDay = simpleDateFormat.parse("2023-7-16");
        Lottery lastLottery = this.getOne(new QueryWrapper<Lottery>().eq("activityTime", simpleDateFormat.format(lastDay)));
        responseMap.setFlag(true);
        responseMap.setData(lastLottery);
        responseMap.setMessage("查询最后一日剩余红包数成功！");
        return responseMap;
    }
}




