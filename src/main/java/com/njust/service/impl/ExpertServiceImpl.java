package com.njust.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njust.bean.*;
import com.njust.bean.baseBean.ExpRes;
import com.njust.bean.baseBean.Expert;
import com.njust.bean.baseBean.Field;
import com.njust.bean.baseBean.Result;
import com.njust.controller.ExpertController;
import com.njust.dao.ExpertMapperExtend;
import com.njust.dao.ExpertResultMapperExtend;
import com.njust.dao.PaperMapperExtend;
import com.njust.dao.baseDao.ExpResMapper;
import com.njust.dao.baseDao.ExpertMapper;
import com.njust.dao.baseDao.FieldMapper;
import com.njust.dao.baseDao.ResultMapper;
import com.njust.service.ExpertService;
import com.njust.utils.ExpertUtil;
import com.njust.utils.UserUtil;
import org.apache.ibatis.session.RowBounds;
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
    private FieldMapper fieldMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private ExpertResultMapperExtend expertResultMapperExtend;

    @Autowired
    private ExpertMapperExtend expertMapperExtend;

    @Autowired
    private PaperMapperExtend paperMapperExtend;

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

//    @Override
//    public PageInfo<ExpertAndField> getExpert(String order, Integer page, Integer pageSize, Expert expert, String field) throws Exception {
//        try{
//            Example expertExample = new Example(Expert.class);
//            Example.Criteria criteria = expertExample.createCriteria();
//            if (!UserUtil.IsNull(expert.getExpertName())){
//                criteria.andLike("expertName", expert.getExpertName()+"%");
//            }
//            if (expert.getGender()!=null ){
//                criteria.andEqualTo("gender", "%"+expert.getGender()+"%");
//            }
//            if (!UserUtil.IsNull(expert.getContact())){
//                criteria.andLike("contact", "%"+expert.getContact()+"%");
//            }
//            if (!UserUtil.IsNull(expert.getEmail())){
//                criteria.andLike("email", "%"+expert.getEmail()+"%");
//            }
//            if (!UserUtil.IsNull(expert.getEducation())){
//                criteria.andLike("education", "%"+expert.getEducation()+"%");
//            }
//            if (!UserUtil.IsNull(expert.getNation())){
//                criteria.andLike("nation", "%"+expert.getNation()+"%");
//            }
//            if (!UserUtil.IsNull(expert.getPoliticalStatus())){
//                criteria.andLike("politicalStatus", "%"+expert.getPoliticalStatus()+"%");
//            }
//            if (expert.getBirthday()!=null){
//                criteria.andLike("birthday", "%"+expert.getBirthday()+"%");
//            }
//            if (expert.getExpertScore()!=null){
//                criteria.andEqualTo("expertScore", "%"+expert.getExpertScore()+"%");
//            }
//            if (!UserUtil.IsNull(field)){
//                Example fieldExample = new Example(Field.class);
//                Example.Criteria fieldCriteria = fieldExample.createCriteria();
//                fieldCriteria.andLike("fieldName", "%" + field + "%");
//                List<Integer> ids = new ArrayList<>();
//                List<Field> fieldList = new ArrayList<>();
//                fieldList = fieldMapper.selectByExample(fieldExample);
//                for (int i = 0; i < fieldList.size(); i++){
//                    ids.add(fieldList.get(i).getFieidId());
//                }
//                criteria.andIn("fieldId", ids);
//            }
//            PageHelper.startPage(page, pageSize, order);
//            List<Expert> expertList = expertMapper.selectByExample(expertExample);
//            List<ExpertAndField> expertAndFieldList = new ArrayList<>();
//            for (int i = 0; i < expertList.size(); i++){
//                ExpertAndField expertAndField = new ExpertAndField();
//                String fieldName = "";
//                if (expertList.get(i).getFieldId() != null){
//                    fieldName = fieldMapper.selectByPrimaryKey(expertList.get(i).getFieldId()).getFieldName();
//                }
// //               expertAndField.setFieldName(fieldName);
////                expertAndField.setExpert(expertList.get(i));
//                expertAndFieldList.add(expertAndField);
//            }
//            return new PageInfo<>(expertAndFieldList);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
//        }
//    }

    @Override
    public List<Expert> recommend(String expertName, String field, Integer recommendNum) throws Exception {
        try {
            Example expertExample = new Example(Expert.class);
            Example.Criteria expertCriteria = expertExample.createCriteria();
            if(!UserUtil.IsNull(expertName)){
                expertCriteria.andLike("expertName", expertName+"%");
            }
            if (!UserUtil.IsNull(field)){
                Example fieldExample = new Example(Field.class);
                Example.Criteria fieldCriteria = fieldExample.createCriteria();
                fieldCriteria.andLike("fieldName", field + "%");
                List<Integer> ids = new ArrayList<>();
                List<Field> fieldList = new ArrayList<>();
                fieldList = fieldMapper.selectByExample(fieldExample);
                for (int i = 0; i < fieldList.size(); i++){
                    ids.add(fieldList.get(i).getFieidId());
                }
                expertCriteria.andIn("fieldId", ids);
            }
            return expertMapper.selectByExampleAndRowBounds(expertExample, new RowBounds(0,recommendNum));
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
                System.out.println("联系方式："+expert.getContact()+"主页地址："+expert.getHomepage()+"介绍："+expert.getIntroduce()+"标签："+expert.getTag());
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
    public PageInfo<ExpertAndField> getExpListInfo(String expertName, String field, String order, Integer page, Integer pageSize) throws Exception{
        try{
            PageHelper.startPage(page, pageSize);
            if (!UserUtil.IsNull(order)){
                PageHelper.orderBy(order);
            }
            if (!UserUtil.IsNull(expertName) || !UserUtil.IsNull(field)){
//                List<ExpertAndField> list = ;
//                System.out.println(list);
                return new PageInfo<>(expertMapperExtend.selectExpertAndField(expertName,field));
            }else{
                throw new CustomException((ResponseResultEnum.MISSING_DATA));
            }
        }catch (Exception e){
            e.printStackTrace();
            if (e instanceof CustomException){
                throw e;
            }else{
                throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
            }
        }
    }

    @Override
    public PageInfo<Paper> getPaper(String expertName, String field, Integer type,String order, Integer page, Integer pageSize) throws Exception{
        try{
            PageHelper.startPage(page, pageSize);
            if (!UserUtil.IsNull(order)){
                PageHelper.orderBy(order);
            }
            return new PageInfo<>(paperMapperExtend.selectByExpertAndField(expertName,field,type));
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
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
    public ExpertAndField getExpertAndFieldById(Integer expertId) throws Exception {
        return expertMapperExtend.selectExpertAndFieldById(expertId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateExpert(Expert expert) throws Exception {

        //专家名是否存在
        if (!UserUtil.IsNull(expert.getExpertName())){
            //如果专家名改变了，且和别的专家名字重复
            if(!expertMapper.selectByPrimaryKey(expert.getExpertId()).getExpertName().equals(expert.getExpertName().trim()) && new ExpertUtil(expertMapper).IsSameName(expert.getExpertName().trim())){
                throw new CustomException(ResponseResultEnum.SAME_NAME);
            }
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

    @Override
    public List<Expert> getMostInfluential(Integer fieldId, Integer expertNum,String order) {
        Expert expert = new Expert();
        expert.setFieldId(fieldId);
        PageHelper.startPage(0, expertNum);
        PageHelper.orderBy("expert_score desc");
        return new PageInfo<>(expertMapper.select(expert)).getList();
    }

    @Override
    public PageInfo<Result> getExpertPaperInfo(Integer expertId, Integer page, Integer pageSize,String order,Integer type) throws Exception {
        try{
            PageHelper.startPage(page, pageSize, order);
            return new PageInfo<>(expertResultMapperExtend.selectExpertResult(expertId,type));
        }catch (Exception e){
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

}
