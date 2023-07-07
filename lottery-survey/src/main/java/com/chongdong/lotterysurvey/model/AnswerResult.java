package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 答题表
 * @TableName tcd_answerResult
 */
@TableName(value ="tcd_answerResult")
@Data
public class AnswerResult implements Serializable {
    /**
     * 答题信息表id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 得分
     */
    @TableField(value = "answerScore")
    private Integer answerScore;

    /**
     * 用户id
     */
    @TableField(value = "userId")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private LocalDateTime createTime;

    /**
     * 答题时间
     */
    @TableField(value = "answerTime")
    private Integer answerTime;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}