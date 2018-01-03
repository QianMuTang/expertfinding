package com.njust.bean.baseBean;

public class Trace {
    private Integer traceId;

    private Integer userId;

    private Integer expertId;

    public Trace(Integer traceId, Integer userId, Integer expertId) {
        this.traceId = traceId;
        this.userId = userId;
        this.expertId = expertId;
    }

    public Trace() {
        super();
    }

    public Integer getTraceId() {
        return traceId;
    }

    public void setTraceId(Integer traceId) {
        this.traceId = traceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }
}