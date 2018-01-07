package com.njust.bean.baseBean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "exp_org")
public class ExpOrg {
    @Id
    @Column(name = "exp_org_id")
    private Integer expOrgId;

    @Column(name = "expert_id")
    private Integer expertId;

    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "exp_org_start_time")
    private Date expOrgStartTime;

    @Column(name = "exp_org_end_time")
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

    /**
     * @return exp_org_id
     */
    public Integer getExpOrgId() {
        return expOrgId;
    }

    /**
     * @param expOrgId
     */
    public void setExpOrgId(Integer expOrgId) {
        this.expOrgId = expOrgId;
    }

    /**
     * @return expert_id
     */
    public Integer getExpertId() {
        return expertId;
    }

    /**
     * @param expertId
     */
    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
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
     * @return exp_org_start_time
     */
    public Date getExpOrgStartTime() {
        return expOrgStartTime;
    }

    /**
     * @param expOrgStartTime
     */
    public void setExpOrgStartTime(Date expOrgStartTime) {
        this.expOrgStartTime = expOrgStartTime;
    }

    /**
     * @return exp_org_end_time
     */
    public Date getExpOrgEndTime() {
        return expOrgEndTime;
    }

    /**
     * @param expOrgEndTime
     */
    public void setExpOrgEndTime(Date expOrgEndTime) {
        this.expOrgEndTime = expOrgEndTime;
    }
}