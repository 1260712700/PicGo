package com.jp.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName j_comment
 */
@TableName(value ="j_comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 评论类型 (1文章 2留言板)
     */
    private Integer type;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 父评论id
     */
    private Long parentId;

    /**
     * 回复评论id
     */
    private Long replyId;

    /**
     * 评论的内容
     */
    private String commentContent;

    /**
     * 评论用户的id
     */
    private Long commentUserId;

    /**
     * 回复用户的id
     */
    private Long replyUserId;

    /**
     * 是否通过 (0否 1是)
     */
    private Integer isCheck;

    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除（0：未删除，1：已删除）
     */
    private Integer isDeleted;

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
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getReplyId() == null ? other.getReplyId() == null : this.getReplyId().equals(other.getReplyId()))
            && (this.getCommentContent() == null ? other.getCommentContent() == null : this.getCommentContent().equals(other.getCommentContent()))
            && (this.getCommentUserId() == null ? other.getCommentUserId() == null : this.getCommentUserId().equals(other.getCommentUserId()))
            && (this.getReplyUserId() == null ? other.getReplyUserId() == null : this.getReplyUserId().equals(other.getReplyUserId()))
            && (this.getIsCheck() == null ? other.getIsCheck() == null : this.getIsCheck().equals(other.getIsCheck()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getReplyId() == null) ? 0 : getReplyId().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getCommentUserId() == null) ? 0 : getCommentUserId().hashCode());
        result = prime * result + ((getReplyUserId() == null) ? 0 : getReplyUserId().hashCode());
        result = prime * result + ((getIsCheck() == null) ? 0 : getIsCheck().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", typeId=").append(typeId);
        sb.append(", parentId=").append(parentId);
        sb.append(", replyId=").append(replyId);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", commentUserId=").append(commentUserId);
        sb.append(", replyUserId=").append(replyUserId);
        sb.append(", isCheck=").append(isCheck);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}