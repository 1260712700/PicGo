package com.jp.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName j_role
 */
@TableName(value ="j_role")
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色字符
     */
    private String roleKey;

    /**
     * 状态（0：正常，1：停用）
     */
    private Integer status;

    /**
     * 排序
     */
    private Long orderNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField( fill= FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除（0：未删除，1：已删除）
     */
    private Integer isDeleted;

}