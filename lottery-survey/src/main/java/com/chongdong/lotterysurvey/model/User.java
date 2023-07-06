package com.chongdong.lotterysurvey.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户表
 * @TableName tcd_user
 */
@TableName(value ="tcd_user")
@Data
public class User implements Serializable {
    public interface AddUser{}
    public interface UpdateUser{}
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    @NotNull(message = "姓名不能为空!",groups = {AddUser.class})
    @NotNull(message = "用户名不能为空!",groups = {UpdateUser.class})
    @Length(min = 2,max = 12,groups = {AddUser.class})
    @Length(min = 2,max = 12,groups = {UpdateUser.class})
    @TableField(value = "userName")
    private String userName;

    /**
     * 电话号码
     */
    @NotNull(message = "电话号码不能为空!",groups = {AddUser.class})
    @NotNull(message = "电话号码不能为空!",groups = {UpdateUser.class})
    @TableField(value = "userPhone")
    private String userPhone;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空!",groups = {AddUser.class})
    @NotNull(message = "密码不能为空!",groups = {UpdateUser.class})
    @TableField(value = "userPassword")
    private String userPassword;

    /**
     * 年龄
     */
    @PositiveOrZero
    @Max(120)
    @NotNull(message = "年龄不能为空!",groups = {AddUser.class})
    @NotNull(message = "年龄不能为空!",groups = {UpdateUser.class})
    @TableField(value = "userAge")
    private Integer userAge;

    /**
     * 区域
     */
    @NotNull(message = "区域不能为空!",groups = {AddUser.class})
    @NotNull(message = "区域不能为空!",groups = {UpdateUser.class})
    @TableField(value = "userRegion")
    private String userRegion;

    /**
     * 答题次数
     */
    @TableField(value = "userNumber")
    private Integer userNumber;

    /**
     * 抽奖次数
     */
    @TableField(value = "userDrawNumber")
    private Integer userDrawNumber;

    /**
     * 创建时间
     */
    @TableField(value = "createDate")
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getUserAge() == null ? other.getUserAge() == null : this.getUserAge().equals(other.getUserAge()))
            && (this.getUserRegion() == null ? other.getUserRegion() == null : this.getUserRegion().equals(other.getUserRegion()))
            && (this.getUserNumber() == null ? other.getUserNumber() == null : this.getUserNumber().equals(other.getUserNumber()))
            && (this.getUserDrawNumber() == null ? other.getUserDrawNumber() == null : this.getUserDrawNumber().equals(other.getUserDrawNumber()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getUserAge() == null) ? 0 : getUserAge().hashCode());
        result = prime * result + ((getUserRegion() == null) ? 0 : getUserRegion().hashCode());
        result = prime * result + ((getUserNumber() == null) ? 0 : getUserNumber().hashCode());
        result = prime * result + ((getUserDrawNumber() == null) ? 0 : getUserDrawNumber().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userAge=").append(userAge);
        sb.append(", userRegion=").append(userRegion);
        sb.append(", userNumber=").append(userNumber);
        sb.append(", userDrawNumber=").append(userDrawNumber);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}