package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 市区表
 * @TableName tcd_city
 */
@TableName(value ="tcd_city")
@Data
public class City implements Serializable {
    /**
     * 市区id
     */
    @TableId(type = IdType.AUTO)
    private Integer cityid;

    /**
     * 市区名称
     */
    private String cityname;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}