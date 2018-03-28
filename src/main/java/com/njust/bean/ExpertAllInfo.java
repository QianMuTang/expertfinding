package com.njust.bean;

import com.njust.bean.baseBean.Expert;
import com.njust.bean.baseBean.Result;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class ExpertAllInfo extends Expert{

    private List<Result> resultList;

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

}
