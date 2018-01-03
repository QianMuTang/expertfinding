package com.njust.bean.baseBean;

public class Field {
    private Integer fieidId;

    private String fieldName;

    private String fieldDesc;

    public Field(Integer fieidId, String fieldName, String fieldDesc) {
        this.fieidId = fieidId;
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    public Field() {
        super();
    }

    public Integer getFieidId() {
        return fieidId;
    }

    public void setFieidId(Integer fieidId) {
        this.fieidId = fieidId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc == null ? null : fieldDesc.trim();
    }
}