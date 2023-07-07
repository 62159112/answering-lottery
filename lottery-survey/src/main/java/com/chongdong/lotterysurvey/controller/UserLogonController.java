package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logon")
public class UserLogonController {
    @Resource
    private UserService userService;
    /**
     * 登录账号
     * */
    @GetMapping("/user/{userPhone}/{userPassword}")
    public ResponseMap userByLong(
            @PathVariable String userPhone,
            @PathVariable String userPassword,
            HttpServletResponse response
    ){
        ResponseMap responseMap = userService.userLongByPhone(userPhone, userPassword);
        User user = (User)responseMap.getData();
        Cookie cookieId=new Cookie("userId",String.valueOf(user.getId()));
        Cookie cookiePhone=new Cookie("userPhone",user.getUserPhone());
        Cookie cookiePassword=new Cookie("userPassword",user.getUserPassword());
        response.addCookie(cookieId);
        response.addCookie(cookiePhone);
        response.addCookie(cookiePassword);
        return responseMap;
    }
    /**
     *注册+添加用户
     * */
    @PostMapping("/AddUser")
    public ResponseMap AddUser(@Validated(User.AddUser.class) @RequestBody User user){
        return userService.addUserById(user);
    }
}
