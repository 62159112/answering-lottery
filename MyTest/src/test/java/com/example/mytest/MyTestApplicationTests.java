package com.example.mytest;

import com.example.mytest.model.Orders;
import com.example.mytest.service.OrdersService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
class MyTestApplicationTests {

    @Resource
    private OrdersService orderService;
    @Test
    void contextLoads() {
        Orders order = new Orders();
        order.setUserid(1);
        order.setTotalAmount(BigDecimal.valueOf(6666));
        order.setCreateTime(new Date());
        orderService.getBaseMapper().insert(order);
    }

}
