package com.chongdong.lotterysurvey.service.impl;


import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.mapper.AnswerResultMapper;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_answerResult(答题结果表)】的数据库操作Service实现
* @createDate 2023-07-06 14:50:26
*/
@Service
public class AnswerResultServiceImpl extends ServiceImpl<AnswerResultMapper, AnswerResult>
    implements AnswerResultService {

    private ResponseMap responseMap = MapFactory.createMap();

    @Resource
    private UserService userService;
    @Resource
    private AnswerResultMapper answerResultMapper;




    @Override
    public ResponseMap add(AnswerResult answerResult, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Integer id = null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();

            if(name.equals("userId")){
                String value = cookie.getValue();
                id = Integer.parseInt(value);
            }
        }
        //修改答题次数和抽奖次数
        User user = userService.getById(id);
        //修改答题次数
        user.setUserNumber(user.getUserNumber()-1);
        if (answerResult.getAnswerScore()>=60){
            //修改抽奖次数
            user.setUserDrawNumber(user.getUserDrawNumber()+1);
        }
        userService.updateById(user);

        //今天答题次数
        Date date = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String format = dateFormat.format(date);
        QueryWrapper<AnswerResult> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",id).like("createTime",format);
        int aLong = answerResultMapper.selectCount(wrapper).intValue();
        System.out.println(aLong+"dddddddddddddddddddd");
        if (aLong<3){
            //今天答题次数+1
          answerResult.setAnswerSequence(aLong+1);
        }
        //结束时间
        answerResult.setEndTime(LocalDateTime.now());

        answerResult.setCreateTime(LocalDateTime.now());
        //添加答题结果
        int insert = answerResultMapper.insert(answerResult);
        if (insert>=1){
            responseMap.setData(insert);
            responseMap.setFlag(true);
            responseMap.setMessage("答题成绩添加成功！");
        }else {
            responseMap.setData(insert);
            responseMap.setFlag(false);
            responseMap.setMessage("答题成绩添加失败！");
        }

        return responseMap;
    }

    @Override
    public ResponseMap selectScore(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        Integer id = null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();

            if(name.equals("userId")){
                String value = cookie.getValue();
                id = Integer.parseInt(value);
            }
        }
        QueryWrapper<AnswerResult> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",id).select("IFNULL(sum(answerScore),0) as totalScore");
       // List<AnswerResult> answerResults = answerResultMapper.selectList(wrapper);
        Map<String,Object> map = this.getMap(wrapper);
        if (map!=null&&!map.isEmpty()){
            responseMap.setData(map);
            responseMap.setFlag(true);
            responseMap.setMessage("答题成绩查询成功！");
        }else {
            responseMap.setData(null);
            responseMap.setFlag(false);
            responseMap.setMessage("答题成绩查询失败！");
        }

        return responseMap;
    }

    @Override
    public Integer searchSpendTimeById(Integer id) {
        String time = baseMapper.searchSpendTimeById(id);
        String[] my =time.split(":");
        int hour =Integer.parseInt(my[0]);
        int min =Integer.parseInt(my[1]);
        int sec =Integer.parseInt(my[2]);
        return hour* 3600 +min* 60 +sec;
    }
}




