package com.njust.dao.baseDao;

import com.njust.bean.baseBean.ExpExp;

public interface ExpExpMapper {
    int deleteByPrimaryKey(Integer expExpId);

    int insert(ExpExp record);

    int insertSelective(ExpExp record);

    ExpExp selectByPrimaryKey(Integer expExpId);

    int updateByPrimaryKeySelective(ExpExp record);

    int updateByPrimaryKey(ExpExp record);
}