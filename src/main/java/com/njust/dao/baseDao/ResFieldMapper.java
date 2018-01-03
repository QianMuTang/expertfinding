package com.njust.dao.baseDao;

import com.njust.bean.baseBean.ResField;

public interface ResFieldMapper {
    int deleteByPrimaryKey(Long resFieldId);

    int insert(ResField record);

    int insertSelective(ResField record);

    ResField selectByPrimaryKey(Long resFieldId);

    int updateByPrimaryKeySelective(ResField record);

    int updateByPrimaryKey(ResField record);
}