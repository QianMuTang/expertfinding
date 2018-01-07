package com.njust.bean.baseBean;

import javax.persistence.*;

public class Org {
    @Id
    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_score")
    private Double orgScore;

    public Org(Integer orgId, String orgName, Double orgScore) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgScore = orgScore;
    }

    public Org() {
        super();
    }

    /**
     * @return org_id
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * @return org_name
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * @return org_score
     */
    public Double getOrgScore() {
        return orgScore;
    }

    /**
     * @param orgScore
     */
    public void setOrgScore(Double orgScore) {
        this.orgScore = orgScore;
    }
}