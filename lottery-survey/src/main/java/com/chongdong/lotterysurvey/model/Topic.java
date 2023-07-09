package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目
 * @TableName tcd_topic
 */
@TableName(value ="tcd_topic")
@Data
public class Topic implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 题目内容
     */
    private String topicContent;

    /**
     * 选项a
     */
    private String optionsA;

    /**
     * 选项b
     */
    private String optionsB;

    /**
     * 选项c
     */
    private String optionsC;

    /**
     * 选项d
     */
    private String optionsD;


    /**
     * 答案
     */
    private String answer;
    /**
     * 创建时间
     */
    private String createTime;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}