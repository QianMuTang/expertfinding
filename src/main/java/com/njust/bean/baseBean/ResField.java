package com.njust.bean.baseBean;

import javax.persistence.*;

@Table(name = "res_field")
public class ResField {
    @Id
    @Column(name = "res_field_id")
    private Long resFieldId;

    @Column(name = "result_id")
    private Long resultId;

    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "res_field_relate")
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

    /**
     * @return res_field_id
     */
    public Long getResFieldId() {
        return resFieldId;
    }

    /**
     * @param resFieldId
     */
    public void setResFieldId(Long resFieldId) {
        this.resFieldId = resFieldId;
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

    /**
     * @return field_id
     */
    public Integer getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     */
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return res_field_relate
     */
    public Double getResFieldRelate() {
        return resFieldRelate;
    }

    /**
     * @param resFieldRelate
     */
    public void setResFieldRelate(Double resFieldRelate) {
        this.resFieldRelate = resFieldRelate;
    }
}