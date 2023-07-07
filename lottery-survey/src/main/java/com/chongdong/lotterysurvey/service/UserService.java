package com.chongdong.lotterysurvey.service;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_user(用户表)】的数据库操作Service
* @createDate 2023-07-06 10:51:34
*/
public interface UserService extends IService<User> {
    /**
     * 注册添加数据
     * */
    ResponseMap addUserById(User user);
    /**
     * 删除
     * */
    ResponseMap deleteByUserId(Integer id);
    /**
     * 登录
     * */
    ResponseMap userLongByPhone(String userPhone,String userPassword);
    /**
     * 修改答题次数
     * */
    ResponseMap userUpdateNumber(Integer id,Integer userNumber);
    /**
     * 修改抽奖次数
     * */
    ResponseMap updateUserDrawNumber(Integer id,Integer userDrawNumber);
}
