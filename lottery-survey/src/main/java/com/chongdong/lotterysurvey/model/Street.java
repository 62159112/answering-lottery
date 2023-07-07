package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tcd_street
 */
@TableName(value ="tcd_street")
@Data
public class Street implements Serializable {
    @TableId("streetId")
    private Integer streetid;

    private String streetname;

    private Integer cityid;

    private static final long serialVersionUID = 1L;
}