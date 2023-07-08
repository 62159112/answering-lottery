package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.AnswerResult;
import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.model.User;
import com.chongdong.lotterysurvey.service.AnswerResultService;
import com.chongdong.lotterysurvey.service.GradesService;
import com.chongdong.lotterysurvey.mapper.GradesMapper;
import com.chongdong.lotterysurvey.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author wo
* @description 针对表【tcd_grades(成绩表（个人成绩）)】的数据库操作Service实现
* @createDate 2023-07-07 11:51:40
*/
@Service
public class IGradesService extends ServiceImpl<GradesMapper, Grades>
    implements GradesService{
    @Resource
    UserService userService;
    @Resource
    AnswerResultService answerResultService;
    @Override
    public Integer queryTeamNumber(Integer answerDay, String region) {
        return baseMapper.searchAllByAnswerDay(answerDay,region);
    }

    @Override
    public Integer queryGradesExit(Integer userId, Integer score, Integer spendTime, Integer answerDay) {
        return baseMapper.searchGradesExit(userId,score,spendTime,answerDay);
    }

    @Override
    public Page<Grades> queryAllGradesOrderByAscAnswerDay() {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime", "MIN(regtime) as regtime","region","answerDay")
                .groupBy("userid", "username","region", "answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .orderByAsc("regtime");

        // 查询前十名成绩
        Page<Grades> page = new Page<>(1, 50);
        return this.page(page, queryWrapper);
    }

    @Override
    public Page<Grades> queryAllByAnswerDayOrderByScore(Integer answerDay) {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime", "MIN(regtime) as regtime","region","answerDay")
                .groupBy("userid", "username","region","answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .orderByAsc("regtime")
                .eq("answerDay",answerDay);
        // 查询前十名成绩
        Page<Grades> page = new Page<>(1, 50);
        return this.page(page, queryWrapper);
    }

    @Override
    public Page<Grades> queryAllMaxScoreByUsername(String username) {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime","region","answerDay")
                .groupBy("userid", "username","region","answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .eq("username",username);

        // 查询前十条成绩
        Page<Grades> page = new Page<>(1, 10);
        return this.page(page, queryWrapper);
    }

    @Override
    public Page<Grades> queryAllByUsername(String username) {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .eq("username",username);
        // 查询前十条成绩
        Page<Grades> page = new Page<>(1, 30);
        return this.page(page, queryWrapper);
    }

    @Override
    public Map flushed(Integer userId, Integer answerDay) {
        // 设置注册时间
        User user = userService.getOne(new QueryWrapper<User>().eq("id", userId));
        QueryWrapper<AnswerResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.between("createTime", LocalDate.of(2023, 7, answerDay),LocalDate.of(2023, 7, answerDay+1));
        List<AnswerResult> answerResultList = answerResultService.list(queryWrapper);
        Map map = new HashMap();
        int i =0;
        for (AnswerResult answerResult:answerResultList) {
            if (this.queryGradesExit(answerResult.getUserId(),answerResult.getAnswerScore(),answerResult.getAnswerTime(),answerResult.getCreateTime().getDayOfMonth())<1){
                boolean save = this.save(createGrades(user, answerResult));
                String saveKey = "save"+ i++;
                map.put(saveKey,save);
            }else {
                Grades grades = createGrades(user, answerResult);
                QueryWrapper<Grades> updateWrapper = new QueryWrapper<>();
                updateWrapper.eq("userId",grades.getUserid());
                updateWrapper.eq("score",grades.getScore());
                updateWrapper.eq("spendTime",grades.getSpendtime());
                updateWrapper.eq("answerDay",answerResult.getCreateTime().getDayOfMonth());
                boolean update = this.update(grades, updateWrapper);
                String updateKey = "update"+ i++;
                map.put(updateKey,update);
            }
        }
        return map;
    }

    private Grades createGrades(User user, AnswerResult answerResult) {
        Grades grades = new Grades();
        grades.setRegtime(user.getCreateDate());
        grades.setUsername(user.getUserName());
        grades.setRegion(user.getUserRegion());
        grades.setUserid(user.getId());
        grades.setSpendtime(answerResult.getAnswerTime());
        grades.setScore(answerResult.getAnswerScore());
        grades.setAnswerday(answerResult.getCreateTime().getDayOfMonth());
        return grades;
    }
}




