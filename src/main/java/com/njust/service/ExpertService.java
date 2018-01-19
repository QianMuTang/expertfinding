package com.njust.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExpertService {

    //根据专家id上传头像
    void icoUpload(Integer expertId, MultipartFile file) throws Exception;

    //根据专家id显示头像地址
    String icoShow(Integer expertId) throws Exception;
}
