package com.njust.dao.baseDao;

import com.njust.bean.baseBean.PersistentLogins;

public interface PersistentLoginsMapper {
    int deleteByPrimaryKey(String series);

    int insert(PersistentLogins record);

    int insertSelective(PersistentLogins record);

    PersistentLogins selectByPrimaryKey(String series);

    int updateByPrimaryKeySelective(PersistentLogins record);

    int updateByPrimaryKey(PersistentLogins record);
}