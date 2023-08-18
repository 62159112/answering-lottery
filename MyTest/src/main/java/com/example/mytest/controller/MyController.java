package com.example.mytest.controller;

import com.example.mytest.model.*;
import com.example.mytest.service.*;
import com.example.mytest.utils.AopAnnotation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MyController {
    @Resource
    UserService userService;
    @Resource
    CartService cartService;
    @Resource
    ProductService productService;
    @Resource
    OrdersService orderService;

    @AopAnnotation
    @GetMapping ("/login/{username}/{password}")
    public String login(@PathVariable String username,@PathVariable String password){
        return userService.login(username,password);
    }
    @PostMapping("/addProduct")
    public Boolean addProduct(@RequestBody ProductMap productMap){
        return productService.add(productMap.getProduct(),productMap.getUserId());
    }
    @DeleteMapping("/deleteProduct/{productId}/{userId}")
    public Boolean deleteProduct(@PathVariable Integer productId,@PathVariable Integer userId){
        return productService.delete(productId,userId);
    }
    @PutMapping("/updateProduct")
    public Boolean updateProduct(@RequestBody ProductMap productMap){
        return productService.update(productMap.getProduct(),productMap.getUserId());
    }
    @GetMapping("/addCart/{productId}/{number}/{userId}")
    public Boolean addCart(@PathVariable Integer productId,@PathVariable Integer number,@PathVariable Integer userId){
        return cartService.addCart(productId,number,userId);
    }
    @DeleteMapping("/addCart/{productId}/{number}/{userId}")
    public Boolean deleteCart(@PathVariable Integer productId,@PathVariable Integer number,@PathVariable Integer userId){
        return cartService.deleteCart(productId,number,userId);
    }
    @GetMapping("/cartList/{userId}")
    public List<Cart> listCart(@PathVariable Integer userId){
        return cartService.listCart(userId);
    }
    @GetMapping("/pay/{userId}")
    public Boolean payOrder(@PathVariable Integer userId){
        Orders order = orderService.getOrder(userId);
        //orderTask.TimeOut(order);
        return orderService.payOrder(userId);
    }
    @GetMapping("/getOrder/{userId}")
    public Orders getOrder(@PathVariable Integer userId){
        return orderService.getOrder(userId);
    }
    @GetMapping("/getPro/{productId}/{userId}")
    public Product getPro(@PathVariable Integer productId,@PathVariable Integer userId){
        return productService.getPro(productId,userId);
    }
    @GetMapping("/productList")
    public List<Product> productList(){
        return productService.listPro();
    }
}
