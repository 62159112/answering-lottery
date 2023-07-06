package com.chongdong.lotterysurvey.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.factory.MapFactory;
import com.chongdong.lotterysurvey.mapper.AnswerResultMapper;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
    private AnswerResultMapper answerResultMapper;




    @Override
    public ResponseMap add(AnswerResult answerResult) {
        int insert = answerResultMapper.insert(answerResult);
        if (insert>=1){
            //答题次数减一

            if (answerResult.getAnswerScore()>=60){
                //抽奖次数加一
            }
        }
        responseMap.setData(insert);
        responseMap.setFlag(true);
        responseMap.setMessage("答题成绩添加成功！");
        return responseMap;
    }
}




