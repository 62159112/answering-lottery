package com.example.mytest.service;

import com.example.mytest.model.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author cd
* @description 针对表【cart】的数据库操作Service
* @createDate 2023-07-18 16:03:41
*/
public interface CartService extends IService<Cart> {
    Boolean addCart(Integer productId,Integer number,Integer userId);

    Boolean deleteCart(Integer productId,Integer number,Integer userId);

    List<Cart> listCart(Integer userId);
}
