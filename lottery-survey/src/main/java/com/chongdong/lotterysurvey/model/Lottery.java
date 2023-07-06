package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tcd_lottery
 */
@TableName(value ="tcd_lottery")
@Data
public class Lottery implements Serializable {
    private Integer lotteryid;

    private Integer prizeone;

    private Integer prizetwo;

    private Integer prizethree;

    private Date activitytime;

    private static final long serialVersionUID = 1L;
}