package com.example.mytest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mytest.model.Cart;
import com.example.mytest.model.Product;
import com.example.mytest.service.CartService;
import com.example.mytest.mapper.CartMapper;
import com.example.mytest.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author cd
 * @description 针对表【cart】的数据库操作Service实现
 * @createDate 2023-07-18 15:22:48
 */
@Service
public class ICartService extends ServiceImpl<CartMapper, Cart>
        implements CartService {
    @Resource
    private ProductService productService;

    @Override
    public Boolean addCart(Integer productId, Integer number, Integer userId) {
        Product product = productService.getById(productId);
        if (product.getStock() >= number) {
            Cart cart = new Cart();
            cart.setProductName(product.getName());
            cart.setProductPrice(product.getPrice());
            cart.setUserid(userId);
            cart.setProductNumber(BigDecimal.valueOf(number));
            cart.setStatus(0);
            save(cart);
            product.setStock(product.getStock() - number);
            return productService.updateById(product);
        }
        return null;
    }

    @Override
    public Boolean deleteCart(Integer productId, Integer number, Integer userId) {
        Product product = productService.getById(productId);
        Cart cart = this.getOne(new QueryWrapper<Cart>().eq("userId", userId).eq("product_name", product.getName()));
        cart.setProductNumber(cart.getProductNumber().subtract(BigDecimal.valueOf(number)));
        if (cart.getProductNumber().equals(BigDecimal.valueOf(0))){
            removeById(cart);
        }
        updateById(cart);
        product.setStock(product.getStock() + number);
        return productService.updateById(product);
    }

    @Override
    public List<Cart> listCart(Integer userId) {
        return this.list((new QueryWrapper<Cart>().eq("userId", userId).eq("status",0)));
    }
}




