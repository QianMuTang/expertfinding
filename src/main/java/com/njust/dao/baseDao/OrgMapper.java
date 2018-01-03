package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Org;

public interface OrgMapper {
    int deleteByPrimaryKey(Integer orgId);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Integer orgId);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
}