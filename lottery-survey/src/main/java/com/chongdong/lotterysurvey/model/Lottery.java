package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tcd_lottery
 */
@TableName(value ="tcd_lottery")
@Data
public class Lottery implements Serializable {
    /**
     * 抽奖id
     */
    @TableId(type = IdType.AUTO)
    private Integer lotteryid;

    /**
     * 奖品1余量
     */
    private Integer prizeone;

    /**
     * 奖品2余量
     */
    private Integer prizetwo;

    /**
     * 奖品3余量
     */
    private Integer prizethree;

    /**
     * 活动时间
     */
    private Date activitytime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}