package com.chongdong.lotterysurvey.mapper;

import com.chongdong.lotterysurvey.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author cd
* @description 针对表【tcd_user(用户表)】的数据库操作Mapper
* @createDate 2023-07-06 10:51:34
* @Entity com.chongdong.lotterysurvey.model.User
*/
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询电话号码
     * */
    User userQueryByPhone(String userPhone);
    /**
     * 登录long
     * */
    User userLongQueryPhonePassword(@Param("userPhone") String userPhone, @Param("userPassword") String userPassword);
}




