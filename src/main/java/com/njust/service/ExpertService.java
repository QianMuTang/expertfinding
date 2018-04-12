package com.njust.service;

import com.github.pagehelper.PageInfo;
import com.njust.bean.ExpertAndField;
import com.njust.bean.Paper;
import com.njust.bean.baseBean.Expert;
import com.njust.bean.ExpertAllInfo;
import com.njust.bean.baseBean.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExpertService {

    //根据专家id上传头像
    void icoUpload(Integer expertId, MultipartFile file) throws Exception;

    //根据专家id显示头像地址
    String icoShow(Integer expertId) throws Exception;

//    //获取专家列表
//    PageInfo<ExpertAndField> getExpert(String order, Integer page, Integer pageSize, Expert expert, String field) throws Exception;

    //根据用户输入的专家名或领域进行推荐
    List<Expert> recommend(String expertName, String field, Integer recommendNum) throws Exception;

    //管理员添加一个专家
    int addExpert(Expert expert) throws Exception;

    //管理员通过id查询一个专家
    Expert getExpertById(Integer expertId) throws Exception;

    //管理员通过id更新一个专家信息
    int updateExpert(Expert expert) throws Exception;

    //管理员通过id删除专家信息
    int deleteExpert(String expertIds) throws Exception;

    //根据领域id查询专家信息
    List<Expert> getMostInfluential(Integer fieldId, Integer expertNum, String order);

    //根据专家id显示专家所有信息
    ExpertAndField  getExpertAndFieldById(Integer expertId) throws Exception;

    //根据专家id查询成果
    PageInfo<Result> getExpertPaperInfo(Integer expertId, Integer page, Integer pageSize, String order, Integer type) throws Exception;

    //根据用户输入的专家名或领域显示专家列表中的专家相关信息
    PageInfo<ExpertAndField> getExpListInfo(String expertName, String field, String order, Integer page, Integer pageSize) throws Exception;

    //根据用户输入的专家名或领域显示论文、专利或获奖列表
    PageInfo<Paper> getPaper(String expertName, String field, Integer type, String order, Integer page, Integer pageSize) throws Exception;
}
