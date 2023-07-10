package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongdong.lotterysurvey.model.*;
import com.chongdong.lotterysurvey.service.*;
import com.chongdong.lotterysurvey.mapper.GradesMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
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
    /*@Resource
    private UserService userService;*/
    /*@Resource
    private AnswerResultService answerResultService;*/
    @Resource
    private StreetService streetService;
    @Resource
    private TeamService teamService;

    @Override
    public Integer queryTeamNumber(Integer answerDay, String region) {
        return baseMapper.searchAllByAnswerDay(answerDay,region);
    }

    @Override
    public Integer queryGradesExit(Integer userId, Integer score, Integer spendTime, Integer answerDay) {
        return baseMapper.searchGradesExit(userId,score,spendTime,answerDay);
    }

    @Override
    public List<Grades> queryAllGradesOrderByAscAnswerDay() {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime", "MIN(regtime) as regtime","region","answerDay")
                .groupBy("userid", "username","region", "answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .orderByAsc("regtime");
        return this.list(queryWrapper);
    }

    @Override
    public List<Grades> queryAllByAnswerDayOrderByScore(Integer answerDay) {
        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime", "MIN(regtime) as regtime","region","answerDay")
                .groupBy("userid", "username","region","answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .orderByAsc("regtime")
                .eq("answerDay",answerDay);
        return this.list(queryWrapper);
    }

    @Override
    public List<Grades> queryAllMaxScoreByUsername(HttpServletRequest request) {
        // 更新今日最新个人成绩数据
//        flushed(getUserId(request), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "username", "MAX(score) as score", "MIN(spendtime) as spendtime","region","answerDay")
                .groupBy("userid", "username","region","answerDay")
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .orderByAsc("spendtime")
                .eq("userid",getUserId(request));

        return this.list(queryWrapper);
    }

    @Override
    public List<Grades> queryAllByUsername(HttpServletRequest request) {
        // 更新今日最新个人成绩数据
//        flushed(getUserId(request), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        // 查询条件，根据总分、答题用时和注册日期进行排序
        QueryWrapper<Grades> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByAsc("answerDay")
                .orderByDesc("score")
                .eq("userid",getUserId(request));
        return this.list(queryWrapper);
    }
    // 刷新信息
    @Override
    public Map flushed(Integer userId, Integer answerDay,User user,List<AnswerResult> answerResultList) {
        // 设置注册时间
        Map map = new HashMap();
        int i =0;
        for (AnswerResult answerResult:answerResultList) {
            if (this.queryGradesExit(answerResult.getUserId(),answerResult.getAnswerScore(),answerResult.getAnswerTime(),answerResult.getCreateTime().getDayOfMonth())<1){
                boolean save = this.save(createGrades(user, answerResult));
                if (save){
                    Team team = new Team();
                    team.setAnswerday(answerDay);
                    team.setStreetid(teamService.queryStreetIdByTeamName(user.getUserRegion()));
                    ResponseMap add = this.add(team);
                    map.put("更新地区数据",add);
                }
                String saveKey = "save"+ i++;
                map.put(saveKey,save);
                map.put("region",user.getUserRegion());
            }else {
                Grades grades = createGrades(user, answerResult);
                QueryWrapper<Grades> updateWrapper = new QueryWrapper<>();
                updateWrapper.eq("userId",grades.getUserid());
                updateWrapper.eq("score",grades.getScore());
                updateWrapper.eq("spendTime",grades.getSpendtime());
                updateWrapper.eq("answerDay",answerResult.getCreateTime().getDayOfMonth());
                boolean update = this.update(grades, updateWrapper);
                if (update){
                    Team team = new Team();
                    team.setAnswerday(answerDay);
                    team.setStreetid(teamService.queryStreetIdByTeamName(user.getUserRegion()));
                    ResponseMap add = this.add(team);
                    map.put("更新地区数据",add);
                }
                String updateKey = "update"+ i++;
                map.put(updateKey,update);
                map.put("region",user.getUserRegion());
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
    private Integer getUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Integer userId = null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(name.equals("userId")){
                String value = cookie.getValue();
                userId = Integer.parseInt(value);
            }
        }
        return userId;
    }

    @Override
    public ResponseMap add(Team team) {
        if (teamService.queryTeamExit(team.getAnswerday(),team.getStreetid())<1){
            // 添加团队
            String streetFullName = streetService.queryStreetFullName(team.getStreetid());
            // 设置答题日期（几号）
            team.setTeamname(streetFullName);
            // team.setAnswerday(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            // 设置答题人次
            team.setTeamnumber(this.queryTeamNumber(team.getAnswerday(), streetFullName));
            boolean save = teamService.save(team);
            return save?ResponseMap.ok().message("添加成功").data(this.queryTeamNumber(team.getAnswerday(), streetFullName)) :ResponseMap.error().message("添加失败");
        }else {
            // 更新团队
            String streetFullName = streetService.queryStreetFullName(team.getStreetid());
            // 设置答题日期（几号）
            team.setTeamname(streetFullName);
            // team.setAnswerday(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            // 设置答题人次
            System.out.println(this.queryTeamNumber(team.getAnswerday(), streetFullName));
            team.setTeamnumber(this.queryTeamNumber(team.getAnswerday(), streetFullName));
            QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("answerDay",team.getAnswerday());
            queryWrapper.eq("teamName",streetFullName);
            queryWrapper.eq("streetId",team.getStreetid());
            boolean update = teamService.update(team, queryWrapper);
            return update?ResponseMap.ok().message("更新成功").data(this.queryTeamNumber(team.getAnswerday(), streetFullName)) :ResponseMap.error().message("更新失败");
        }
    }
}
