package com.jp.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName j_message_list
 */
@TableName(value ="j_message_list")
@Data
public class MessageList implements Serializable {
    /**
     * 消息列表id，自增长
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID （关联到用户表的user_id）
     */
    private Long userId;

    /**
     * 联系人ID（对于单聊，是对方的用户ID；对于群聊，是群组ID）
     */
    private Long contactId;

    /**
     * 最后一条消息的ID 关联到消息表的message_id
     */
    private Long lastMessageId;

    /**
     * 未读消息数
     */
    private Integer unreadCount;

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
        MessageList other = (MessageList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getContactId() == null ? other.getContactId() == null : this.getContactId().equals(other.getContactId()))
            && (this.getLastMessageId() == null ? other.getLastMessageId() == null : this.getLastMessageId().equals(other.getLastMessageId()))
            && (this.getUnreadCount() == null ? other.getUnreadCount() == null : this.getUnreadCount().equals(other.getUnreadCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getContactId() == null) ? 0 : getContactId().hashCode());
        result = prime * result + ((getLastMessageId() == null) ? 0 : getLastMessageId().hashCode());
        result = prime * result + ((getUnreadCount() == null) ? 0 : getUnreadCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", contactId=").append(contactId);
        sb.append(", lastMessageId=").append(lastMessageId);
        sb.append(", unreadCount=").append(unreadCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}