package com.njust.bean.baseBean;

import java.util.Date;

public class ExpOrg {
    private Integer expOrgId;

    private Integer expertId;

    private Integer orgId;

    private Date expOrgStartTime;

    private Date expOrgEndTime;

    public ExpOrg(Integer expOrgId, Integer expertId, Integer orgId, Date expOrgStartTime, Date expOrgEndTime) {
        this.expOrgId = expOrgId;
        this.expertId = expertId;
        this.orgId = orgId;
        this.expOrgStartTime = expOrgStartTime;
        this.expOrgEndTime = expOrgEndTime;
    }

    public ExpOrg() {
        super();
    }

    public Integer getExpOrgId() {
        return expOrgId;
    }

    public void setExpOrgId(Integer expOrgId) {
        this.expOrgId = expOrgId;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getExpOrgStartTime() {
        return expOrgStartTime;
    }

    public void setExpOrgStartTime(Date expOrgStartTime) {
        this.expOrgStartTime = expOrgStartTime;
    }

    public Date getExpOrgEndTime() {
        return expOrgEndTime;
    }

    public void setExpOrgEndTime(Date expOrgEndTime) {
        this.expOrgEndTime = expOrgEndTime;
    }
}