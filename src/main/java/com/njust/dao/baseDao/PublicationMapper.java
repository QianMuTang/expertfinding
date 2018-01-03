package com.njust.dao.baseDao;

import com.njust.bean.baseBean.Publication;

public interface PublicationMapper {
    int deleteByPrimaryKey(Integer publicationId);

    int insert(Publication record);

    int insertSelective(Publication record);

    Publication selectByPrimaryKey(Integer publicationId);

    int updateByPrimaryKeySelective(Publication record);

    int updateByPrimaryKey(Publication record);
}