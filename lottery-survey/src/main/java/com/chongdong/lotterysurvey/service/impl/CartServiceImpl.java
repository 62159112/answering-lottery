package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.Cart;
import com.chongdong.lotterysurvey.service.CartService;
import com.chongdong.lotterysurvey.mapper.CartMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【cart】的数据库操作Service实现
* @createDate 2023-07-18 15:22:07
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

}




