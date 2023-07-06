package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
   @Resource
    private IUserService userService;
    /**
     * 删除单个用户
     * */
    @DeleteMapping("/deleteUserId/{id}")
    public ResponseMap deleteUserId(@PathVariable Integer id){
        return userService.deleteByUserId(id);
    }
    /**
     *注册+添加用户
     * */
    @PostMapping("/AddUser")
    public ResponseMap AddUser(@Validated(User.AddUser.class) @RequestBody User user){

        return userService.addUserById(user);
    }
    /**
     * 登录账号
     * */
    @GetMapping("/userByLong/{userPhone}/{userPassword}")
    public ResponseMap userByLong(
            @PathVariable String userPhone,
            @PathVariable String userPassword
    ){
        return userService.userLongByPhone(userPhone, userPassword);
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
