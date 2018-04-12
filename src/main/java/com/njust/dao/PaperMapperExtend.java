package com.njust.dao;


import com.njust.bean.Paper;
import com.njust.bean.baseBean.Result;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface PaperMapperExtend {
    List<Paper> selectByExpertAndField(@Param("expertName") String expertName, @Param("field") String field, @Param("type") Integer type);
}

