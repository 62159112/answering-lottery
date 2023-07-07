package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 镇/街道表
 * @TableName tcd_street
 */
@TableName(value ="tcd_street")
@Data
public class Street implements Serializable {
    /**
     * 镇或街道id
     */
    @TableId(type = IdType.AUTO)
    private Integer streetid;

    /**
     * 镇或街道名称
     */
    private String streetname;

    /**
     * 镇或街道所属市区id
     */
    private Integer cityid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}