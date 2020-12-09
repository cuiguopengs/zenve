package com.zenve.chat.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "group_info")
public class Group extends BaseEntity{
    private List<User> users;
    private String profile;
    private List<MessageInfo> messageInfos;

    @Transient
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Transient
    public List<MessageInfo> getMessageInfos() {
        return messageInfos;
    }

    public void setMessageInfos(List<MessageInfo> messageInfos) {
        this.messageInfos = messageInfos;
    }
}

