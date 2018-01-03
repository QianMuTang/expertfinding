package com.njust.dao.baseDao;

import com.njust.bean.baseBean.ExpRes;

public interface ExpResMapper {
    int deleteByPrimaryKey(Long expResId);

    int insert(ExpRes record);

    int insertSelective(ExpRes record);

    ExpRes selectByPrimaryKey(Long expResId);

    int updateByPrimaryKeySelective(ExpRes record);

    int updateByPrimaryKey(ExpRes record);
}