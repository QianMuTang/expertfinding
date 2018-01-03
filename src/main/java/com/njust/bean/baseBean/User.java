package com.njust.bean.baseBean;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private Integer privLevel;

    private String userEmail;

    private Integer isPush;

    public User(Integer userId, String userName, String password, Integer privLevel, String userEmail, Integer isPush) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.privLevel = privLevel;
        this.userEmail = userEmail;
        this.isPush = isPush;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPrivLevel() {
        return privLevel;
    }

    public void setPrivLevel(Integer privLevel) {
        this.privLevel = privLevel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }
}