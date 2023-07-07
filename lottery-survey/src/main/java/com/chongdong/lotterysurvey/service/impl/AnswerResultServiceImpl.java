package com.chongdong.lotterysurvey.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.mapper.AnswerResultMapper;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private IUserService userService;
    @Resource
    private AnswerResultMapper answerResultMapper;




    @Override
    public ResponseMap add(AnswerResult answerResult) {
        Date date = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String format = dateFormat.format(date);
        QueryWrapper<AnswerResult> wrapper = new QueryWrapper<>();
        wrapper.like("createTime",format);
        int aLong = answerResultMapper.selectCount(wrapper).intValue();
        System.out.println(aLong);

        if (aLong<3){
            //答题次数
          answerResult.setAnswerSequence(aLong+1);
        }
        //结束时间
        answerResult.setEndTime(LocalDateTime.now());
        //添加答题结果
        int insert = answerResultMapper.insert(answerResult);
        if (insert>=1){
            //答题次数减一
            User user = userService.getById(answerResult.getUserId());
            user.setUserNumber(user.getUserNumber()-1);
            userService.updateById(user);
            if (answerResult.getAnswerScore()>=60){
                //抽奖次数加一
                user.setUserDrawNumber(user.getUserDrawNumber()+1);
            }
            userService.updateById(user);
        }
        responseMap.setData(insert);
        responseMap.setFlag(true);
        responseMap.setMessage("答题成绩添加成功！");
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




