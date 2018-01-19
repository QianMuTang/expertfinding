package com.njust.controller;

import com.njust.bean.ResponseResult;
import com.njust.service.ExpertService;
import com.njust.utils.ResponseResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    //根据专家id上传头像
    @PostMapping(value = "/expert/icoUpload/{expertId}")
    public ResponseResult icoUpload(@PathVariable(value = "expertId") Integer expertId, @RequestParam("uploadIco")MultipartFile file) throws Exception{
        expertService.icoUpload(expertId, file);
        return ResponseResultUtil.success();
    }

    //根据专家id显示头像路径
    @GetMapping(value = "/expert/icoShow/{expertId}")
    public ResponseResult icoShow(@PathVariable(value = "expertId") Integer expertId) throws Exception{
        return ResponseResultUtil.success(expertService.icoShow(expertId));
    }
}
