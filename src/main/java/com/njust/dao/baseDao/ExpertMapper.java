package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Expert;

public interface ExpertMapper {
    int deleteByPrimaryKey(Integer expertId);

    int insert(Expert record);

    int insertSelective(Expert record);

    Expert selectByPrimaryKey(Integer expertId);

    int updateByPrimaryKeySelective(Expert record);

    int updateByPrimaryKeyWithBLOBs(Expert record);

    int updateByPrimaryKey(Expert record);
}