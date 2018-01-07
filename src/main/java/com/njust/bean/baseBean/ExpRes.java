package com.njust.bean.baseBean;

import javax.persistence.*;

@Table(name = "exp_res")
public class ExpRes {
    @Id
    @Column(name = "exp_res_id")
    private Long expResId;

    @Column(name = "expert_id")
    private Integer expertId;

    @Column(name = "result_id")
    private Long resultId;

    public ExpRes(Long expResId, Integer expertId, Long resultId) {
        this.expResId = expResId;
        this.expertId = expertId;
        this.resultId = resultId;
    }

    public ExpRes() {
        super();
    }

    /**
     * @return exp_res_id
     */
    public Long getExpResId() {
        return expResId;
    }

    /**
     * @param expResId
     */
    public void setExpResId(Long expResId) {
        this.expResId = expResId;
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
     * @return result_id
     */
    public Long getResultId() {
        return resultId;
    }

    /**
     * @param resultId
     */
    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }
}