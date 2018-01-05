package com.njust.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="securityconfig")
public class SecuritySettings {
    private String login = "/login";
    private String logoutsuccssurl = "/";
    private String permitall = "/";
    private String urlroles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogoutsuccssurl() {
        return logoutsuccssurl;
    }

    public void setLogoutsuccssurl(String logoutsuccssurl) {
        this.logoutsuccssurl = logoutsuccssurl;
    }

    public String getPermitall() {
        return permitall;
    }

    public void setPermitall(String permitall) {
        this.permitall = permitall;
    }

    public String getUrlroles() {
        return urlroles;
    }

    public void setUrlroles(String urlroles) {
        this.urlroles = urlroles;
    }
}
