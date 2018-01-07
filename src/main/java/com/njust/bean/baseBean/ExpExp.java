package com.njust.bean.baseBean;

import javax.persistence.*;

@Table(name = "exp_exp")
public class ExpExp {
    @Id
    @Column(name = "exp_exp_id")
    private Integer expExpId;

    @Column(name = "expert1_id")
    private Integer expert1Id;

    @Column(name = "expert2_id")
    private Integer expert2Id;

    @Column(name = "exp_relate")
    private Double expRelate;

    public ExpExp(Integer expExpId, Integer expert1Id, Integer expert2Id, Double expRelate) {
        this.expExpId = expExpId;
        this.expert1Id = expert1Id;
        this.expert2Id = expert2Id;
        this.expRelate = expRelate;
    }

    public ExpExp() {
        super();
    }

    /**
     * @return exp_exp_id
     */
    public Integer getExpExpId() {
        return expExpId;
    }

    /**
     * @param expExpId
     */
    public void setExpExpId(Integer expExpId) {
        this.expExpId = expExpId;
    }

    /**
     * @return expert1_id
     */
    public Integer getExpert1Id() {
        return expert1Id;
    }

    /**
     * @param expert1Id
     */
    public void setExpert1Id(Integer expert1Id) {
        this.expert1Id = expert1Id;
    }

    /**
     * @return expert2_id
     */
    public Integer getExpert2Id() {
        return expert2Id;
    }

    /**
     * @param expert2Id
     */
    public void setExpert2Id(Integer expert2Id) {
        this.expert2Id = expert2Id;
    }

    /**
     * @return exp_relate
     */
    public Double getExpRelate() {
        return expRelate;
    }

    /**
     * @param expRelate
     */
    public void setExpRelate(Double expRelate) {
        this.expRelate = expRelate;
    }
}