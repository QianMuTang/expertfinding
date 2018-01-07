package com.njust.bean.baseBean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "exp_loc")
public class ExpLoc {
    @Id
    @Column(name = "exp_loc_id")
    private Integer expLocId;

    @Column(name = "expert_id")
    private Integer expertId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "exp_loc_start_time")
    private Date expLocStartTime;

    @Column(name = "exp_loc_end_time")
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

    /**
     * @return exp_loc_id
     */
    public Integer getExpLocId() {
        return expLocId;
    }

    /**
     * @param expLocId
     */
    public void setExpLocId(Integer expLocId) {
        this.expLocId = expLocId;
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
     * @return location_id
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * @param locationId
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * @return exp_loc_start_time
     */
    public Date getExpLocStartTime() {
        return expLocStartTime;
    }

    /**
     * @param expLocStartTime
     */
    public void setExpLocStartTime(Date expLocStartTime) {
        this.expLocStartTime = expLocStartTime;
    }

    /**
     * @return exp_loc_end_time
     */
    public Date getExpLocEndTime() {
        return expLocEndTime;
    }

    /**
     * @param expLocEndTime
     */
    public void setExpLocEndTime(Date expLocEndTime) {
        this.expLocEndTime = expLocEndTime;
    }
}