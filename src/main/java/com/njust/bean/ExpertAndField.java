package com.njust.bean;

import com.njust.bean.baseBean.Expert;
import com.njust.bean.baseBean.Field;

public class ExpertAndField extends Expert{
    private Field field;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
