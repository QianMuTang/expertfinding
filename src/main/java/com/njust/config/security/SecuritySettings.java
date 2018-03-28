package com.njust.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="securityconfig")
public class SecuritySettings {
    private String rememberme;
    private String login = "/api/login";
    private String login_page = "/login";
    private String logoutsuccssurl = "/";
    private String staticresources;
    private String permitall = "/";
    private String urlroles;

    public String getRememberme() {
        return rememberme;
    }

    public void setRememberme(String rememberme) {
        this.rememberme = rememberme;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin_page() {
        return login_page;
    }

    public void setLogin_page(String login_page) {
        this.login_page = login_page;
    }

    public String getLogoutsuccssurl() {
        return logoutsuccssurl;
    }

    public void setLogoutsuccssurl(String logoutsuccssurl) {
        this.logoutsuccssurl = logoutsuccssurl;
    }

    public String getStaticresources() {
        return staticresources;
    }

    public void setStaticresources(String staticresources) {
        this.staticresources = staticresources;
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
