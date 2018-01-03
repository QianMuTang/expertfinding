package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Result;

public interface ResultMapper {
    int deleteByPrimaryKey(Long resultId);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Long resultId);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}