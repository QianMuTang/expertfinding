package com.njust.dao;

import com.njust.bean.ExpertAndField;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExpertMapperExtend {

    List<ExpertAndField> selectExpertAndField(@Param("expertName") String expertName, @Param("field") String field);

    ExpertAndField selectExpertAndFieldById(@Param("expertId") Integer expertId);
}
