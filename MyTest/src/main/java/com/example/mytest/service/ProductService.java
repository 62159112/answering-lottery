package com.example.mytest.service;

import com.example.mytest.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mytest.model.User;

import java.util.List;

/**
* @author cd
* @description 针对表【product】的数据库操作Service
* @createDate 2023-07-18 15:22:51
*/
public interface ProductService extends IService<Product> {
    Boolean add(Product product, Integer userId);

    Boolean delete(Integer productId,Integer userId);

    Boolean update(Product product, Integer userId);

    List<Product> listPro();

    Product getPro(Integer productId,Integer userId);
}
