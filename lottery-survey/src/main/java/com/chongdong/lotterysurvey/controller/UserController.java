package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
   @Resource
    private UserService userService;
    /**
     * 删除单个用户
     * */
    @DeleteMapping("/deleteUserId/{id}")
    public ResponseMap deleteUserId(@PathVariable Integer id){
        return userService.deleteByUserId(id);
    }
    /**
     * 修改答题次数
     * */
    @PutMapping("/updateUserNumber/{id}/{userNumber}")
    public ResponseMap updateUser(
            @PathVariable Integer id,
            @PathVariable Integer userNumber
    ){
        return  userService.userUpdateNumber(id, userNumber);
    }
    /**
     * 修改抽奖次数
     * */
    @PutMapping("/updateUserDrawNumber/{id}/{userDrawNumber}")
    public ResponseMap updateUserDrawNumber(
            @PathVariable Integer id,
            @PathVariable Integer userDrawNumber
    ){
        return userService.updateUserDrawNumber(id,userDrawNumber);
    }
}
