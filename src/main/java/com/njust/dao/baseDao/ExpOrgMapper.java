package com.njust.dao.baseDao;

import com.njust.bean.baseBean.ExpOrg;

public interface ExpOrgMapper {
    int deleteByPrimaryKey(Integer expOrgId);

    int insert(ExpOrg record);

    int insertSelective(ExpOrg record);

    ExpOrg selectByPrimaryKey(Integer expOrgId);

    int updateByPrimaryKeySelective(ExpOrg record);

    int updateByPrimaryKey(ExpOrg record);
}