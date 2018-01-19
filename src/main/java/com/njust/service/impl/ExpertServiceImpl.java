package com.njust.service.impl;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.Expert;
import com.njust.controller.ExpertController;
import com.njust.dao.baseDao.ExpertMapper;
import com.njust.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ExpertServiceImpl implements ExpertService{
    private static final Logger logger = LoggerFactory.getLogger(ExpertController.class);

    @Autowired
    private ExpertMapper expertMapper;

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
}
