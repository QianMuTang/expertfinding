package com.njust.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.ExpRes;
import com.njust.bean.baseBean.Expert;
import com.njust.bean.ExpertAllInfo;
import com.njust.bean.baseBean.Result;
import com.njust.controller.ExpertController;
import com.njust.dao.baseDao.ExpResMapper;
import com.njust.dao.baseDao.ExpertMapper;
import com.njust.dao.baseDao.ResultMapper;
import com.njust.service.ExpertService;
import com.njust.utils.ExpertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService{
    private static final Logger logger = LoggerFactory.getLogger(ExpertController.class);

    @Autowired
    private ExpertMapper expertMapper;

    @Autowired
    private ExpResMapper expResMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Value("${web.upload-path}")
    private String filePath;

    @Override
    public void icoUpload(Integer expertId, MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new CustomException(ResponseResultEnum.EMPTY_FILE);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重命名文件
        fileName = expertId.toString()+"_ico"+suffixName;
        // 文件上传后的路径
        logger.info("文件上传后的路径"+filePath);
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //拷贝到该目录
            file.transferTo(dest);
            //保存路径
            Expert expert = new Expert();
            expert.setExpertId(expertId);
            expert.setIcoName(fileName);
            expertMapper.updateByPrimaryKeySelective(expert);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.UPLOAD_FAIL);
        }
    }

    @Override
    public String icoShow(Integer expertId) throws Exception {
        try{
            return expertMapper.selectByPrimaryKey(expertId).getIcoName();
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    public PageInfo<Expert> getExpert(String order, Integer page, Integer pageSize, Expert expert) throws Exception {
        try{
            Example example = new Example(Expert.class);
            Example.Criteria criteria = example.createCriteria();
            if (expert.getExpertName() != null && !expert.getExpertName().trim().equals("")){
                criteria.andLike("expertName", "%"+expert.getExpertName()+"%");
            }
            if (expert.getGender()!=null ){
                criteria.andEqualTo("gender", "%"+expert.getGender()+"%");
            }
            if (expert.getContact()!=null && !expert.getContact().trim().equals("")){
                criteria.andLike("contact", "%"+expert.getContact()+"%");
            }
            if (expert.getEmail()!=null && !expert.getEmail().trim().equals("")){
                criteria.andLike("email", "%"+expert.getEmail()+"%");
            }
            if (expert.getEducation()!=null && !expert.getEducation().trim().equals("")){
                criteria.andLike("education", "%"+expert.getEducation()+"%");
            }
            if (expert.getNation()!=null && !expert.getNation().trim().equals("")){
                criteria.andLike("nation", "%"+expert.getNation()+"%");
            }
            if (expert.getPoliticalStatus()!=null && !expert.getPoliticalStatus().trim().equals("")){
                criteria.andLike("politicalStatus", "%"+expert.getPoliticalStatus()+"%");
            }
            if (expert.getBirthday()!=null){
                criteria.andLike("birthday", "%"+expert.getBirthday()+"%");
            }
            if (expert.getExpertScore()!=null){
                criteria.andEqualTo("expertScore", "%"+expert.getExpertScore()+"%");
            }
            PageHelper.startPage(page, pageSize, order);
            return new PageInfo<>(expertMapper.selectByExample(example));
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    public int addExpert(Expert expert) throws Exception {
        try{
            //插入专家信息
            if (expertMapper.selectOne(expert) == null){
                return expertMapper.insertSelective(expert);
            }else{
                throw new CustomException(ResponseResultEnum.SAME_NAME);
            }
        }catch (Exception e){
            e.printStackTrace();
            if (e instanceof CustomException){
                throw e;
            }else{
                throw new CustomException(ResponseResultEnum.INSERT_ERROR);
            }
        }
    }

    @Override
    public Expert getExpertById(Integer expertId) throws Exception {
        Expert expert = new Expert();
        expert.setExpertId(expertId);
        try{
            return expertMapper.selectOne(expert);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }

    }

    @Override
    public ExpertAllInfo getExpertAllInfo(Integer expertId) throws Exception {
       Expert expert=expertMapper.selectByPrimaryKey(expertId);

       ExpertAllInfo expertAllInfo=new ExpertAllInfo();
       expertAllInfo.setExpertId(expertId);
       expertAllInfo.setExpertName(expert.getExpertName());
       expertAllInfo.setEducation(expert.getEducation());
       expertAllInfo.setBirthday(expert.getBirthday());
       expertAllInfo.setPoliticalStatus(expert.getPoliticalStatus());
       expertAllInfo.setContact(expert.getContact());

        ExpRes expRes=new ExpRes();
        expRes.setExpertId(expertId);
        List<ExpRes> expResList=expResMapper.select(expRes);
        List<Result> resultList=new ArrayList<>();

        for(int i=0;i<expResList.size();i++){
            Result result=resultMapper.selectByPrimaryKey(expResList.get(i).getResultId());
           resultList.add(result);
        }

        expertAllInfo.setResultList(resultList);

        return expertAllInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateExpert(Expert expert) throws Exception {
        if (expert.getExpertName()==null || expert.getExpertName().trim().equals("")){
            throw new CustomException(ResponseResultEnum.MISSING_DATA);
        }
        //如果专家名改变了，且和别的专家名字重复
        if(!expertMapper.selectByPrimaryKey(expert.getExpertId()).getExpertName().equals(expert.getExpertName().trim()) && new ExpertUtil(expertMapper).IsSameName(expert.getExpertName().trim())){
            throw new CustomException(ResponseResultEnum.SAME_NAME);
        }
        try {
            expertMapper.updateByPrimaryKeySelective(expert);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.UPDATE_ERROR);
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteExpert(String expertIds) throws Exception {
        try {
            String[] expertIdArray = expertIds.split("&");
            int count = 0;
            for (String expertId : expertIdArray) {
                    count += expertMapper.deleteByPrimaryKey(Integer.parseInt(expertId.trim()));
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.DELETE_ERROR);
        }
    }

}
