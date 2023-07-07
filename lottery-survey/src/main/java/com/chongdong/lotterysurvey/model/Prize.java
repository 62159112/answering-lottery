package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @TableName tcd_prize
 */
@TableName(value ="tcd_prize")
@Data
public class Prize implements Serializable {
    private Integer prizeid;

    private Integer userid;

    private BigDecimal prize;

    private String userphone;

    private static final long serialVersionUID = 1L;
}