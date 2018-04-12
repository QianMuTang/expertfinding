package com.njust.dao;

import com.njust.bean.baseBean.Result;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExpertResultMapperExtend extends Mapper<Result> {

    List<Result> selectExpertResult(@Param("expertId") Integer expertId, @Param("type") Integer type);
}
