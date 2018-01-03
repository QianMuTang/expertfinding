package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Location;

public interface LocationMapper {
    int deleteByPrimaryKey(Integer locationId);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(Integer locationId);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);
}