package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Field;

public interface FieldMapper {
    int deleteByPrimaryKey(Integer fieidId);

    int insert(Field record);

    int insertSelective(Field record);

    Field selectByPrimaryKey(Integer fieidId);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);
}