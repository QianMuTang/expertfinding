package com.njust.dao.baseDao;

import com.njust.bean.baseBean.ExpLoc;

public interface ExpLocMapper {
    int deleteByPrimaryKey(Integer expLocId);

    int insert(ExpLoc record);

    int insertSelective(ExpLoc record);

    ExpLoc selectByPrimaryKey(Integer expLocId);

    int updateByPrimaryKeySelective(ExpLoc record);

    int updateByPrimaryKey(ExpLoc record);
}