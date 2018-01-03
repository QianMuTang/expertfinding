package com.njust.bean.baseBean;

public class ResField {
    private Long resFieldId;

    private Long resultId;

    private Integer fieldId;

    private Double resFieldRelate;

    public ResField(Long resFieldId, Long resultId, Integer fieldId, Double resFieldRelate) {
        this.resFieldId = resFieldId;
        this.resultId = resultId;
        this.fieldId = fieldId;
        this.resFieldRelate = resFieldRelate;
    }

    public ResField() {
        super();
    }

    public Long getResFieldId() {
        return resFieldId;
    }

    public void setResFieldId(Long resFieldId) {
        this.resFieldId = resFieldId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Double getResFieldRelate() {
        return resFieldRelate;
    }

    public void setResFieldRelate(Double resFieldRelate) {
        this.resFieldRelate = resFieldRelate;
    }
}