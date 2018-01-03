package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Importance;

public interface ImportanceMapper {
    int deleteByPrimaryKey(Long importanceId);

    int insert(Importance record);

    int insertSelective(Importance record);

    Importance selectByPrimaryKey(Long importanceId);

    int updateByPrimaryKeySelective(Importance record);

    int updateByPrimaryKey(Importance record);
}