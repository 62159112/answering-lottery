package com.chongdong.lotterysurvey.task;

import com.chongdong.lotterysurvey.service.LotteryService;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class LotteryTask {
    @Resource
    private LotteryService lotteryService;
    @Resource
    private UserService userService;
    /**
     * 将昨日剩余奖池添加至今日
     * */
    @Scheduled(cron = "0 0 0 * * ?")
    public void getYesterdayLottery(){
        lotteryService.addLotteryFirst();
        userService.setUserNumber();
    }
    /**
     * 9.20定时添加奖池任务
     * */
    @Scheduled(cron = "0 20 9 * * ?")
    public void firstLottery(){
        lotteryService.addLottery();
    }
    /**
     * 11.45定时添加奖池任务
     * */
    @Scheduled(cron = "0 45 11 * * ?")
    public void secondLottery(){
        lotteryService.addLottery();
    }
    /**
     * 14.25定时添加奖池任务
     * */
    @Scheduled(cron = "0 25 14 * * ?")
    public void thirdLottery(){
        lotteryService.addLottery();
    }
    /**
     * 16.45定时添加奖池任务
     * */
    @Scheduled(cron = "0 45 16 * * ?")
    public void fourthLottery(){
        lotteryService.addLottery();
    }
    /**
     * 18.35定时添加奖池任务
     * */
    @Scheduled(cron = "0 35 18 * * ?")
    public void fifthLottery(){
        lotteryService.addLottery();
    }
}
