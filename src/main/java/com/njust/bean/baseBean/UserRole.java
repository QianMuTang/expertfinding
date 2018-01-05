package com.njust.bean.baseBean;

public class UserRole {
    private Integer id;

    private Integer uid;

    private Integer rid;

    public UserRole(Integer id, Integer uid, Integer rid) {
        this.id = id;
        this.uid = uid;
        this.rid = rid;
    }

    public UserRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}