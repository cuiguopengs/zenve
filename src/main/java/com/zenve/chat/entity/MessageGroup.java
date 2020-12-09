package com.zenve.chat.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "message_group")
public class MessageGroup extends BaseEntity{
    private Long messageId;
    private Long groupId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
