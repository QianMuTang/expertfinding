package com.njust.bean.baseBean;

public class ExpExp {
    private Integer expExpId;

    private Integer expert1Id;

    private Integer expert2Id;

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

    public Integer getExpExpId() {
        return expExpId;
    }

    public void setExpExpId(Integer expExpId) {
        this.expExpId = expExpId;
    }

    public Integer getExpert1Id() {
        return expert1Id;
    }

    public void setExpert1Id(Integer expert1Id) {
        this.expert1Id = expert1Id;
    }

    public Integer getExpert2Id() {
        return expert2Id;
    }

    public void setExpert2Id(Integer expert2Id) {
        this.expert2Id = expert2Id;
    }

    public Double getExpRelate() {
        return expRelate;
    }

    public void setExpRelate(Double expRelate) {
        this.expRelate = expRelate;
    }
}