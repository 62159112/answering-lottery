package com.example.mytest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mytest.model.Product;
import com.example.mytest.model.User;
import com.example.mytest.service.ProductService;
import com.example.mytest.mapper.ProductMapper;
import com.example.mytest.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author cd
* @description 针对表【product】的数据库操作Service实现
* @createDate 2023-07-18 15:22:51
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{
    @Resource
    private UserService userService;

    @Override
    public Boolean add(Product product, Integer userId) {
        User user = userService.getById(userId);
        if (user.getUsername().equals("admin")){
            return this.save(product);
        }
        return false;
    }

    @Override
    public Boolean delete(Integer productId, Integer userId) {
        User user = userService.getById(userId);
        if (user.getUsername().equals("admin")){
            return this.removeById(productId);
        }
        return false;
    }

    @Override
    public Boolean update(Product product, Integer userId) {
        User user = userService.getById(userId);
        if (user.getUsername().equals("admin")){
            return this.updateById(product);
        }
        return false;
    }

    @Override
    public List<Product> listPro() {
        return this.list();
    }

    @Override
    public Product getPro(Integer productId,Integer userId) {
        User user = userService.getById(userId);
        if (user.getUsername().equals("admin")){
            return getById(productId);
        }
        return null;
    }
}




