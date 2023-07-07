package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tcd_city
 */
@TableName(value ="tcd_city")
@Data
public class City implements Serializable {
    @TableId("cityId")
    private Integer cityid;

    private String cityname;

    private static final long serialVersionUID = 1L;
}