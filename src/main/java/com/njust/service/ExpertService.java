package com.njust.service;

import com.github.pagehelper.PageInfo;
import com.njust.bean.baseBean.Expert;
import com.njust.bean.ExpertAllInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ExpertService {

    //根据专家id上传头像
    void icoUpload(Integer expertId, MultipartFile file) throws Exception;

    //根据专家id显示头像地址
    String icoShow(Integer expertId) throws Exception;

    //获取专家列表
    PageInfo<Expert> getExpert(String order, Integer page, Integer pageSize, Expert expert) throws Exception;

    //根据专家id显示专家所有信息，包括成果
    ExpertAllInfo  getExpertAllInfo(Integer expertId) throws Exception;

    //管理员添加一个专家
    int addExpert(Expert expert) throws Exception;

    //管理员通过id查询一个专家
    Expert getExpertById(Integer expertId) throws Exception;

    //管理员通过id更新一个专家信息
    int updateExpert(Expert expert) throws Exception;

    //管理员通过id删除专家信息
    int deleteExpert(String expertIds) throws Exception;
}
