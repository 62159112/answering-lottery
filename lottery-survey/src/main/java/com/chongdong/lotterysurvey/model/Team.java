package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tcd_team
 */
@TableName(value ="tcd_team")
@Data
public class Team implements Serializable {
    /**
     * 团体排名id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 团体名称（地域/街道）
     */
    @TableField(value = "team_name")
    private String teamName;

    /**
     * 团队(地域/街道)答题人数
     */
    @TableField(value = "team_number")
    private Integer teamNumber;

    /**
     * 街道id
     */
    @TableField(value = "street_id")
    private Integer streetId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}