package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 成绩表（个人成绩）
 * @TableName tcd_grades
 */
@TableName(value ="tcd_grades")
@Data
public class Grades implements Serializable {
    /**
     * 成绩id
     */
    @TableId(value = "gradesId", type = IdType.AUTO)
    private Integer gradesid;

    /**
     * 用户id
     */
    @TableField(value = "userId")
    private Integer userid;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 得分（答题成绩）
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 用时（答题花费时间）
     */
    @TableField(value = "spendTime")
    private Integer spendtime;

    /**
     * 注册时间
     */
    @TableField(value = "regtime")
    private Date regtime;

    /**
     * 所属（地域/街道）
     */
    @TableField(value = "region")
    private String region;

    /**
     * 答题日期（哪一天答的）
     */
    @TableField(value = "answerDay")
    private Integer answerday;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}