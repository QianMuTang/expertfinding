package com.njust.bean.baseBean;

public class ExpRes {
    private Long expResId;

    private Integer expertId;

    private Long resultId;

    public ExpRes(Long expResId, Integer expertId, Long resultId) {
        this.expResId = expResId;
        this.expertId = expertId;
        this.resultId = resultId;
    }

    public ExpRes() {
        super();
    }

    public Long getExpResId() {
        return expResId;
    }

    public void setExpResId(Long expResId) {
        this.expResId = expResId;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }
}