package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.UserService;
import com.chongdong.lotterysurvey.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author cd
* @description 针对表【tcd_user(用户表)】的数据库操作Service实现
* @createDate 2023-07-06 10:51:34
*/
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Resource
    private UserMapper userMapper;
    ResponseMap responseMap= MapFactory.createMap();
    /**
     * 注册账号+添加账号
     * */
    @Override
    public ResponseMap addUserById(User user) {
        user.setCreateDate(new Date());
        user.setUserNumber(2);
        user.setUserDrawNumber(0);
        Pattern compile = Pattern.compile("^1[3456789]\\d{9}$");
        Matcher matcher = compile.matcher(user.getUserPhone());
        //验证电话号码是否为真
        if(matcher.matches()){
            //真
            if(userMapper.userQueryByPhone(user.getUserPhone())==null){
                //是没有账号给予新建
                int insert = userMapper.insert(user);
                //判断账号是否为零如果是那么添加失败
                if(insert==0){
                    //添加失败
                    responseMap.setFlag(false);
                    responseMap.setData(null);
                    responseMap.setMessage("注册失败!");
                }else {
                    //添加成功
                    responseMap.setFlag(true);
                    responseMap.setData(null);
                    responseMap.setMessage("注册成功!");
                }
            }else {
                //有账号不给予新建
                    responseMap.setFlag(false);
                    responseMap.setData(null);
                    responseMap.setMessage("账号已经存在!");
            }
        }else {
            //假
                    responseMap.setFlag(false);
                    responseMap.setData(null);
                    responseMap.setMessage("不存在的电话!");
        }
        return responseMap;
    }
    /**
     * 删除单个用户
     * */
    @Override
    public ResponseMap deleteByUserId(Integer id) {
        int i = userMapper.deleteById(id);
        if(i==0){
            responseMap.setData(null);
            responseMap.setFlag(false);
            responseMap.setMessage("删除失败!");
        }else {
            responseMap.setFlag(true);
            responseMap.setData(null);
            responseMap.setMessage("删除成功!");
        }
        return responseMap;
    }
    /**
     * 登录
     * */
    @Override
    public ResponseMap userLongByPhone(String userPhone, String userPassword) {
        User user = userMapper.userLongQueryPhonePassword(userPhone, userPassword);
        if(user==null){
            responseMap.setFlag(false);
            responseMap.setData(null);
            responseMap.setMessage("该账号未注册请注册!");
        }else {
            responseMap.setFlag(true);
            responseMap.setData(user);
            responseMap.setMessage("登录成功!");
        }
        return responseMap;
    }
    /**
     * 修改答题次数
     * */
    @Override
    public ResponseMap userUpdateNumber(Integer id, Integer userNumber) {
        User user=new User();
        user.setId(id);
        user.setUserNumber(userNumber);
        int i = userMapper.updateById(user);
        if(i==0){
            responseMap.setFlag(false);
            responseMap.setFlag(null);
            responseMap.setMessage("修改失败!");
        }else {
            responseMap.setFlag(true);
            responseMap.setData(null);
            responseMap.setMessage("修改成功");
        }
        return responseMap;
    }
    /**
     * 修改抽奖次数
     * */
    @Override
    public ResponseMap updateUserDrawNumber(Integer id, Integer userDrawNumber) {
        User user=new User();
        user.setId(id);
        user.setUserDrawNumber(userDrawNumber);
        int i = userMapper.updateById(user);
        if(i==0){
            responseMap.setFlag(false);
            responseMap.setFlag(null);
            responseMap.setMessage("修改失败!");
        }else {
            responseMap.setFlag(true);
            responseMap.setData(null);
            responseMap.setMessage("修改成功");
        }
        return responseMap;
    }


    /**
     * 分享朋友圈添加答题次数
     * @param id
     * @return
     */
    @Override
    public ResponseMap updateUserNumber(Integer id) {
        User user = userMapper.selectById(id);
        user.setUserNumber(user.getUserNumber()+1);
        int num = userMapper.updateById(user);
        if(num==0){
            responseMap.setFlag(false);
            responseMap.setFlag(null);
            responseMap.setMessage("修改失败!");
        }else {
            responseMap.setFlag(true);
            responseMap.setData(null);
            responseMap.setMessage("修改成功");
        }
        return responseMap;
    }

}




