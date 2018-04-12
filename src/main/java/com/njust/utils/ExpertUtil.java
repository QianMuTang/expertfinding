package com.njust.utils;

import com.njust.bean.baseBean.Expert;
import com.njust.dao.baseDao.ExpertMapper;

public class ExpertUtil {
    private final ExpertMapper expertMapper;

    public ExpertUtil(ExpertMapper expertMapper) {
        this.expertMapper = expertMapper;
    }

    // 是否重名
    public Boolean IsSameName(String expertName){
        Expert expert = new Expert();
        expert.setExpertName(expertName);
        if(expertMapper.selectOne(expert) == null){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    // 专家是否存在
    public Boolean isExist(Integer expertId){
        if (expertMapper.selectByPrimaryKey(expertId) != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
