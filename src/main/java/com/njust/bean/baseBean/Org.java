package com.njust.bean.baseBean;

public class Org {
    private Integer orgId;

    private String orgName;

    private Double orgScore;

    public Org(Integer orgId, String orgName, Double orgScore) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgScore = orgScore;
    }

    public Org() {
        super();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Double getOrgScore() {
        return orgScore;
    }

    public void setOrgScore(Double orgScore) {
        this.orgScore = orgScore;
    }
}