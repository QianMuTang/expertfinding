package com.njust.controller;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.Expert;
import com.njust.dao.ExpertMapperExtend;
import com.njust.dao.baseDao.ExpertMapper;
import com.njust.service.ExpertService;
import com.njust.utils.ExpertUtil;
import com.njust.utils.ReadJsonUtil;
import com.njust.utils.ResponseResultUtil;
import com.njust.utils.SpiderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    @Autowired
    private ExpertMapper expertMapper;

    @Value("${web.spider-path}")
    private String spiderPath;

    @Value("${web.data-path}")
    private String dataPath;

    @Value("${web.aminer-spider}")
    private String aminerSpider;

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

//    //根据用户输入的专家相关信息获取专家列表
//    @GetMapping(value="/expert")
//    public ResponseResult getExpert(@RequestParam(value = "order", required = false,defaultValue = "expert_id") String order,
//                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
//                                    @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize,
//                                    @RequestParam(value = "field", required = false) String field,
//                                    Expert expert) throws Exception{
//        return ResponseResultUtil.success(expertService.getExpert(order, page, pageSize, expert, field));
//    }
    //根据用户输入的专家名或领域显示专家信息列表
    @GetMapping(value="/expert")
    public ResponseResult getExpert(@RequestParam(value="expertName",required = false) String expertName,
                                         @RequestParam(value="fieldName",required = false) String fieldName,
                                         @RequestParam(value="order",required = false) String order,
                                         @RequestParam(value="page",required=false,defaultValue="1") Integer page,
                                         @RequestParam(value="pageSize",required = false,defaultValue = "4")Integer pageSize) throws Exception{
        return ResponseResultUtil.success(expertService.getExpListInfo(expertName, fieldName, order, page, pageSize));
    }

    //根据用户输入的专家名或领域显示论文/专利/获奖列表
    @GetMapping(value="/expert/result")
    public ResponseResult getPaper(@RequestParam(value="expertName",required = false) String expertName,
                                   @RequestParam(value="fieldName",required = false) String field,
                                   @RequestParam(value="type",required = false, defaultValue = "1") Integer type,
                                   @RequestParam(value="order",required = false) String order,
                                   @RequestParam(value="page",required=false,defaultValue="1") Integer page,
                                   @RequestParam(value="pageSize",required = false,defaultValue = "4")Integer pageSize) throws Exception{
        return ResponseResultUtil.success(expertService.getPaper(expertName, field, type, order, page, pageSize));
    }

    //根据用户输入的专家名或领域进行推荐
    @GetMapping(value = "/expert/recommend")
    public ResponseResult recommend(@RequestParam(value = "field", required = false) String field,
                                    @RequestParam(value = "expertName", required = false) String expertName,
                                    @RequestParam(value = "recommendNum", required = false, defaultValue = "5") Integer recommendNum) throws Exception{
        return ResponseResultUtil.success(expertService.recommend(expertName, field, recommendNum));
    }

    //根据领域id展示最具影响力
    @GetMapping(value = "/expert/mostInfluential")
    public ResponseResult getMostInfluential(@RequestParam(value = "fieldId", defaultValue = "1") Integer fieldId,
                                             @RequestParam(value = "expertNum", defaultValue = "3") Integer expertNum) throws Exception{
        return ResponseResultUtil.success(expertService.getMostInfluential(fieldId, expertNum, "expert_score"));
    }

    // 根据专家id，显示专家所有信息
    @GetMapping(value = "/expert/{expertId}")
    public ResponseResult getExpertAndFieldById(@PathVariable(value = "expertId") Integer expertId) throws Exception{
       return ResponseResultUtil.success(expertService.getExpertAndFieldById(expertId));
    }

    //根据专家id,成果类型，显示专家所有论文或者专利或者获奖情况
    @GetMapping(value = "/expert/result/{expertId}")
    public ResponseResult getExpertPaperInfo(@PathVariable(value = "expertId") Integer expertId,
                                             @RequestParam(value="order",required = false,defaultValue = "result_id") String order,
                                             @RequestParam(value="page",required=false,defaultValue="1") Integer page,
                                             @RequestParam(value="pageSize",required = false,defaultValue = "4")Integer pageSize,
                                             @RequestParam(value="type",required = false,defaultValue = "1") Integer type) throws Exception{
        return ResponseResultUtil.success(expertService.getExpertPaperInfo(expertId,page,pageSize,order,type));
    }

    //爬取专家并插入数据库,isUpdate==0:插入数据，isUpdate==1:更新数据
    @GetMapping(value = "/expert/spider")
    public ResponseResult spiderExpert(@RequestParam(value = "expertName") String expertName,
                                       @RequestParam(value = "isUpdate") Integer isUpdate) throws Exception{
        if (isUpdate == 0){
            //插入数据
            if (!new ExpertUtil(expertMapper).IsSameName(expertName)){
                int value = new SpiderUtil().multiThreadSpider(spiderPath, dataPath, aminerSpider, expertName);
                if (value == 0){
                    //解析专家信息
                    Expert expert = ReadJsonUtil.parseExpert(dataPath + expertName + "_person_info.json");
                    //插入数据库
                    expertService.addExpert(expert);
                    return ResponseResultUtil.success();
                }else{
                    return ResponseResultUtil.error(ResponseResultEnum.SPIDER_FAIL);
                }
            }else{
                return ResponseResultUtil.error(ResponseResultEnum.DONOT_SPIDER);
            }
        }else if(isUpdate == 1){
            //更新数据
            Expert temp = new Expert();
            temp.setExpertName(expertName);
            temp = expertMapper.selectOne(temp);
            if (temp != null){
                int value = new SpiderUtil().multiThreadSpider(spiderPath, dataPath, aminerSpider, expertName);
                if (value == 0){
                    //解析专家信息
                    Expert expert = ReadJsonUtil.parseExpert(dataPath + expertName + "_person_info.json");

                    expert.setExpertId(temp.getExpertId());
                    System.out.println("专家id："+expert.getExpertId());
                    //更新专家信息
                    expertService.updateExpert(expert);
                    return ResponseResultUtil.success();
                }else{
                    return ResponseResultUtil.error(ResponseResultEnum.SPIDER_FAIL);
                }
            }else{
                return ResponseResultUtil.error(ResponseResultEnum.NO_EXPERT);
            }
        }
        return ResponseResultUtil.error(ResponseResultEnum.NO_DECISION);
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
