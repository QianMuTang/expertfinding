package com.njust.bean.baseBean;

import java.util.Date;

public class ExpLoc {
    private Integer expLocId;

    private Integer expertId;

    private Integer locationId;

    private Date expLocStartTime;

    private Date expLocEndTime;

    public ExpLoc(Integer expLocId, Integer expertId, Integer locationId, Date expLocStartTime, Date expLocEndTime) {
        this.expLocId = expLocId;
        this.expertId = expertId;
        this.locationId = locationId;
        this.expLocStartTime = expLocStartTime;
        this.expLocEndTime = expLocEndTime;
    }

    public ExpLoc() {
        super();
    }

    public Integer getExpLocId() {
        return expLocId;
    }

    public void setExpLocId(Integer expLocId) {
        this.expLocId = expLocId;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Date getExpLocStartTime() {
        return expLocStartTime;
    }

    public void setExpLocStartTime(Date expLocStartTime) {
        this.expLocStartTime = expLocStartTime;
    }

    public Date getExpLocEndTime() {
        return expLocEndTime;
    }

    public void setExpLocEndTime(Date expLocEndTime) {
        this.expLocEndTime = expLocEndTime;
    }
}