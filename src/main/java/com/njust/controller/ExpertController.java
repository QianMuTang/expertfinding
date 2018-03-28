package com.njust.controller;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.Expert;
import com.njust.service.ExpertService;
import com.njust.utils.ResponseResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
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

    //根据用户输入的专家相关信息获取专家列表
    @GetMapping(value="/expert")
    public ResponseResult getExpert(@RequestParam(value = "order", required = false,defaultValue = "expert_id") String order,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize,
                                    Expert expert) throws Exception{
        return ResponseResultUtil.success(expertService.getExpert(order, page, pageSize, expert));
    }

    //根据专家id显示专家所有信息，包括成果
    @GetMapping(value = "/expert/{expertId}")
    public ResponseResult getExpertAllInfo(@PathVariable(value = "expertId") Integer expertId) throws Exception{
       return ResponseResultUtil.success(expertService.getExpertAllInfo(expertId));
    }

    //管理员添加一个专家
    @PostMapping(value="/admin/expert")
    public ResponseResult addExpert(Expert expert, @RequestParam(value = "bdStr") String birthday) throws Exception{
        System.out.println("expertName");

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            Date date_birthday = sdf.parse(birthday);
            expert.setBirthday(date_birthday);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.ERROR_DATEFORMAT);
        }
        expertService.addExpert(expert);
        return ResponseResultUtil.success();
    }

    //管理员通过id查询一个专家
    @GetMapping(value="/admin/expert/{expertId}")
    public ResponseResult getExpertById(@PathVariable("expertId") Integer expertId) throws Exception{
        return ResponseResultUtil.success(expertService.getExpertById(expertId));
    }

    //管理员通过id更新一个专家信息
    @PutMapping(value="/admin/expert/{expertId}")
    public ResponseResult updateExpertById(@PathVariable("expertId") Integer userId, Expert expert) throws Exception{
        expert.setExpertId(userId);
        //更新专家属性
        expertService.updateExpert(expert);
        return ResponseResultUtil.success();
    }

    //管理员通过id删除专家信息，支持批量删除
    @DeleteMapping(value="/admin/expert/{expertId}")
    public ResponseResult deleteExpertById(@PathVariable("expertId") String expertIds) throws Exception{
        //删除专家，返回成功删除的个数
        return ResponseResultUtil.success(expertService.deleteExpert(expertIds));
    }

}
