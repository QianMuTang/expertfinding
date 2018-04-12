package com.njust.bean;

import com.njust.bean.baseBean.*;

import java.util.Date;
import java.util.List;

public class Paper extends Result{

    private ExpRes expRes;
    private Expert expert;
    private Publication publication;
    private ResField resField;
    private Field field;

    public ExpRes getExpRes() {
        return expRes;
    }

    public void setExpRes(ExpRes expRes) {
        this.expRes = expRes;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public ResField getResField() { return resField; }

    public void setResField(ResField resField) { this.resField = resField; }

    public Field getField() { return field; }

    public void setField(Field field) { this.field = field; }

}
