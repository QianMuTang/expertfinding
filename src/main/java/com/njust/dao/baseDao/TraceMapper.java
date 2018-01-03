package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Trace;

public interface TraceMapper {
    int deleteByPrimaryKey(Integer traceId);

    int insert(Trace record);

    int insertSelective(Trace record);

    Trace selectByPrimaryKey(Integer traceId);

    int updateByPrimaryKeySelective(Trace record);

    int updateByPrimaryKey(Trace record);
}