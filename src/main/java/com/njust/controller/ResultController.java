package com.njust.controller;

import com.njust.bean.ResponseResult;
import com.njust.service.ResultService;
import com.njust.utils.ReadJsonUtil;
import com.njust.utils.ResponseResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResultController {

    @Autowired
    ResultService resultService;

    @Value("${web.data-path}")
    private String path;

    @PostMapping(value = "/result/insertResult")
    public ResponseResult insertPaper(@RequestParam(value = "type") Integer type) throws Exception{
        //type=1:插入论文, type=2:插入专利
        System.out.println("file path:"+path);
        List<Map<String, Object>> list =  ReadJsonUtil.parseResult(path+"lqm_new.json", type);
        if (type == 1){
            //插入论文
            resultService.insertPaper(list);
        } else if (type == 2){
            //插入专利
        }
        return ResponseResultUtil.success();
    }
}
