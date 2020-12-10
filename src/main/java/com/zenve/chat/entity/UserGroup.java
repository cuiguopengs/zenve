package com.zenve.chat.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_group")
public class UserGroup extends BaseEntity{
    private Long userId;
    private Long groupId;

    public Long getUserId() {
        return userId;

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
