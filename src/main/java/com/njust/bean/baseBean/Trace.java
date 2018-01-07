package com.njust.bean.baseBean;

import javax.persistence.*;

public class Trace {
    @Id
    @Column(name = "trace_id")
    private Integer traceId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "expert_id")
    private Integer expertId;

    public Trace(Integer traceId, Integer userId, Integer expertId) {
        this.traceId = traceId;
        this.userId = userId;
        this.expertId = expertId;
    }

    public Trace() {
        super();
    }

    /**
     * @return trace_id
     */
    public Integer getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     */
    public void setTraceId(Integer traceId) {
        this.traceId = traceId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
}