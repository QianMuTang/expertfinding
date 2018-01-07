package com.njust.bean.baseBean;

import javax.persistence.*;

public class Field {
    @Id
    @Column(name = "fieid_id")
    private Integer fieidId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_desc")
    private String fieldDesc;

    public Field(Integer fieidId, String fieldName, String fieldDesc) {
        this.fieidId = fieidId;
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    public Field() {
        super();
    }

    /**
     * @return fieid_id
     */
    public Integer getFieidId() {
        return fieidId;
    }

    /**
     * @param fieidId
     */
    public void setFieidId(Integer fieidId) {
        this.fieidId = fieidId;
    }

    /**
     * @return field_name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**
     * @return field_desc
     */
    public String getFieldDesc() {
        return fieldDesc;
    }

    /**
     * @param fieldDesc
     */
    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc == null ? null : fieldDesc.trim();
    }
}