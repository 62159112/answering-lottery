package com.chongdong.lotterysurvey.interceptor;

import com.chongdong.lotterysurvey.mapper.UserMapper;
import com.chongdong.lotterysurvey.model.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;
    //进入controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return false;
        }else {
            for (Cookie cookie : cookies) {
                //找到对应的值去查看是否存在该用户如果不存在无法进入其他接口
                if(cookie.getName().equals("userId")){
                    if(userMapper.selectById(Integer.parseInt(cookie.getValue()))==null){
                        return false;
                    }else {
                        return true;
                    }
                }else {
                    continue;
                }
            }
            return true;
        }


    }
}
