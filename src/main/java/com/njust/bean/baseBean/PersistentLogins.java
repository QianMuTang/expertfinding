package com.njust.bean.baseBean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "persistent_logins")
public class PersistentLogins {
    @Id
    private String series;

    private String username;

    private String token;

    @Column(name = "last_used")
    private Date lastUsed;

    public PersistentLogins(String series, String username, String token, Date lastUsed) {
        this.series = series;
        this.username = username;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public PersistentLogins() {
        super();
    }

    /**
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     * @param series
     */
    public void setSeries(String series) {
        this.series = series == null ? null : series.trim();
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * @return last_used
     */
    public Date getLastUsed() {
        return lastUsed;
    }

    /**
     * @param lastUsed
     */
    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}