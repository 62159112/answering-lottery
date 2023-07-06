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
    @TableId(value = "teamId", type = IdType.AUTO)
    private Integer teamid;

    /**
     * 团体名称（地域/街道）
     */
    @TableField(value = "teamName")
    private String teamname;

    /**
     * 团队(地域/街道)答题人数
     */
    @TableField(value = "teamNumber")
    private Integer teamnumber;

    /**
     * 街道id
     */
    @TableField(value = "streetId")
    private Integer streetid;

    /**
     * 答题日期（哪天答的）
     */
    @TableField(value = "answerDay")
    private Integer answerday;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}