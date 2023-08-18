package com.example.mytest.task;

import com.example.mytest.model.Orders;
import com.example.mytest.service.OrdersService;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
public class OrderTask {
    @Resource
    OrdersService ordersService;
    Timer timer = new HashedWheelTimer();
    @Scheduled(fixedRate = 5000)
    public void TimeOut(){
        List<Orders> ordersList = ordersService.list();
        timer.newTimeout(task,6, TimeUnit.SECONDS);
        for (Orders order : ordersList) {
            ordersService.removeById(order);
        }
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("当前全部订单还有30min过期");
        }
    };
}
