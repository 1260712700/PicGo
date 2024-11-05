package com.jp.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author JP
 * @Date 2024/10/30 7:12
 */
@Data
public class UserDetailVo {

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户性别(0,未定义,1,男,2女)
     */
    private Integer gender;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 个人简介
     */
    private String intro;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户最近登录时间
     */
    private Date loginTime;

    /**
     * 是否禁用 (0否 1是)
     */
    private Integer isDisable;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户更新时间
     */
    private Date updateTime;

    /**
     * 是否删除（0：未删除，1：已删除）
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nickname=").append(nickname);
        sb.append(", username=").append(username);
        sb.append(", gender=").append(gender);
        sb.append(", password=").append(password);
        sb.append(", avatar=").append(avatar);
        sb.append(", intro=").append(intro);
        sb.append(", email=").append(email);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", isDisable=").append(isDisable);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
