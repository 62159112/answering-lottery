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
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_answerResult(答题结果表)】的数据库操作Service实现
* @createDate 2023-07-06 14:50:26
*/
@Service
public class IAnswerResultService extends ServiceImpl<AnswerResultMapper, AnswerResult>
    implements com.chongdong.lotterysurvey.service.AnswerResultService {

    private ResponseMap responseMap = MapFactory.createMap();

    @Resource
    private UserService userService;
    @Resource
    private AnswerResultMapper answerResultMapper;

    @Resource
    private GradesService gradesService;



//添加答题成绩
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

        //答题用户
        //answerResult.setUserId(id);
        //答题时间
        answerResult.setCreateTime(LocalDateTime.now());
        //添加答题结果
        int insert = answerResultMapper.insert(answerResult);
        if (insert>=1){
            responseMap.setData(insert);
            responseMap.setFlag(true);
            responseMap.setMessage("答题成绩添加成功！");

            Integer answerDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            User userA = userService.getOne(new QueryWrapper<User>().eq("id", id));
            QueryWrapper<AnswerResult> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userId", id);
            queryWrapper.between("createTime", LocalDate.of(2023, 7, answerDay),LocalDate.of(2023, 7, answerDay+1));
            List<AnswerResult> answerResultList = this.list(queryWrapper);
            gradesService.flushed(id,answerDay,userA,answerResultList);

        }else {
            responseMap.setData(insert);
            responseMap.setFlag(false);
            responseMap.setMessage("答题成绩添加失败！");
        }

        return responseMap;
    }

    //添加答题成绩传id
    @Override
    public ResponseMap add(AnswerResult answerResult) {

        //修改答题次数和抽奖次数
        User user = userService.getById(answerResult.getUserId());
        //修改答题次数
        user.setUserNumber(user.getUserNumber()-1);
        if (answerResult.getAnswerScore()>=60){
            //修改抽奖次数
            user.setUserDrawNumber(user.getUserDrawNumber()+1);
        }
        userService.updateById(user);

        //结束时间
        answerResult.setCreateTime(LocalDateTime.now());

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


//查总成绩
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


    //根据查总成绩
    @Override
    public ResponseMap selectScore(Integer id) {


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
}




