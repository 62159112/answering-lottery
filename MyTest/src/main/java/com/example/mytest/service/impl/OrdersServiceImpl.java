package com.example.mytest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mytest.model.Cart;
import com.example.mytest.model.Order;
import com.example.mytest.model.Orders;
import com.example.mytest.service.CartService;
import com.example.mytest.service.OrdersService;
import com.example.mytest.mapper.OrdersMapper;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author cd
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2023-07-18 17:16:23
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{
    @Resource
    private CartService cartService;
    Timer timer = new HashedWheelTimer();
    @Override
    public Orders getOrder(Integer userId) {
        Orders order = new Orders();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        List<Cart> carts = cartService.listCart(userId);
        for (Cart cart : carts) {
            totalPrice = totalPrice.add(cart.getProductNumber().multiply(cart.getProductPrice()));
        }
        order.setUserid(userId);
        order.setTotalAmount(totalPrice);
        order.setCreateTime(new Date());
        saveOrUpdate(order);
        return order;
    }

    @Override
    public Boolean payOrder(Integer userId) {
        List<Cart> carts = cartService.listCart(userId);
        for (Cart cart : carts) {
            cart.setStatus(1);
        }
        return  cartService.updateBatchById(carts);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("当前全部订单还有30min过期");
        }
    };

    public void orderTimeOut(List<Order> orderList){
        for (Order order : orderList) {
            timer.newTimeout(task,6, TimeUnit.MINUTES);
        }

    }
}




