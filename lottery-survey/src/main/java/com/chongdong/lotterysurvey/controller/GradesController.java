package com.chongdong.lotterysurvey.controller;

import com.chongdong.lotterysurvey.model.Grades;
import com.chongdong.lotterysurvey.model.ResponseMap;
import com.chongdong.lotterysurvey.service.GradesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 个人成绩（答题成绩）
 * Author: wo
 * Created: 2023/7/6 10:43
 * Modified By: wo
 * Last Modified: 2023/7/6 10:43
 */
@RestController
@RequestMapping("/grades")
public class GradesController {
    @Resource
    private GradesService gradesService;
    @GetMapping("/{id}")
    public ResponseMap queryGradesById(@PathVariable Integer id){
        Grades byId = gradesService.getById(id);
        ResponseMap responseMap = new ResponseMap();
        responseMap.setData(byId);
        responseMap.setFlag(true);
        responseMap.setMessage("成功");
        return responseMap;
    }

}
