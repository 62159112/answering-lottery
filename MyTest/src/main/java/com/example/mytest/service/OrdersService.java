package com.example.mytest.service;

import com.example.mytest.model.Order;
import com.example.mytest.model.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cd
* @description 针对表【orders】的数据库操作Service
* @createDate 2023-07-18 17:16:23
*/
public interface OrdersService extends IService<Orders> {
    Orders getOrder(Integer userId);

    Boolean payOrder(Integer userId);
}
