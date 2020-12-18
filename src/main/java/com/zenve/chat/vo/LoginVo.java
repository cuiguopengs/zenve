package com.zenve.chat.vo;

public class LoginVo {
    private String username;
    private String password;
    private Integer appType;

    public LoginVo() {
    }

    public LoginVo(String username, String password, Integer appType) {
        this.username = username;
        this.password = password;
        this.appType = appType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
