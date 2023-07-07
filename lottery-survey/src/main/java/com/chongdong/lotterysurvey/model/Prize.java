package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 奖励表
 * @TableName tcd_prize
 */
@TableName(value ="tcd_prize")
@Data
public class Prize implements Serializable {
    /**
     * 奖励id
     */
    @TableId(type = IdType.AUTO)
    private Integer prizeid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 用户获取奖励金额
     */
    private BigDecimal prize;

    /**
     * 用户电话号码
     */
    private String userphone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}