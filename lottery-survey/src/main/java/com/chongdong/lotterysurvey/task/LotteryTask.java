package com.chongdong.lotterysurvey.task;

import com.chongdong.lotterysurvey.service.LotteryService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class LotteryTask {
    @Resource
    private LotteryService lotteryService;

    @Scheduled(cron = "0 20 9 * * ?")
    public void firstLottery(){
        lotteryService.addLotteryFirst();
    }
    @Scheduled(cron = "0 45 11 * * ?")
    public void secondLottery(){
        lotteryService.addLottery();
    }
    @Scheduled(cron = "0 25 14 * * ?")
    public void thirdLottery(){
        lotteryService.addLottery();
    }
    @Scheduled(cron = "0 45 16 * * ?")
    public void fourthLottery(){
        lotteryService.addLottery();
    }
    @Scheduled(cron = "0 35 18 * * ?")
    public void fifthLottery(){
        lotteryService.addLottery();
    }
}
