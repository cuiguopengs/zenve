package com.zenve.chat.vo;

import com.zenve.chat.entity.Group;

import java.util.List;

public class UserVo {
    private String username;
    private Long id;
    private List<Group> groups;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
