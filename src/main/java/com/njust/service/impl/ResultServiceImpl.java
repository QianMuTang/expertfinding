package com.njust.service.impl;

import com.njust.bean.baseBean.*;
import com.njust.dao.baseDao.*;
import com.njust.service.ResultService;
import com.njust.utils.InsertResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private PublicationMapper publicationMapper;

    @Autowired
    private ExpertMapper expertMapper;

    @Autowired
    private ExpResMapper expResMapper;



    @Override
    public String insertPaper(List<Map<String, Object>> list) throws Exception {
        //首先插入出版源
        System.out.println("list.size()"+list.size());
        int insert_correct = 0;
        int insert_error = 0;
        for (int i = 0; i < list.size(); i++){
            try{
                Map<String, Object> map = list.get(i);
                String result_name = (String) map.get("result_name");
                String result_desc = (String) map.get("result_desc");
                Integer result_type = (Integer) map.get("result_type");
                System.out.println(map.get("result_time"));
                String result_date = (String) map.get("result_time");
                if(result_date.contains("\\")){
                    result_date=result_date.replace("\\","-");
                }

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
                Date result_time=sdf.parse(result_date);
                String authors = (String) map.get("author");
                String publication_name = (String) map.get("publication_name");
                String expertName=(String)map.get("author");
                System.out.println(expertName);

                InsertResultUtil insertResultUtil=new InsertResultUtil(userMapper,resultMapper,publicationMapper,
                        expertMapper,expResMapper);

                insertResultUtil.insertOne(result_name, result_desc,  result_type,  result_time,  publication_name,  expertName);
                insert_correct++;
            }catch (Exception e){
                insert_error++;
                continue;
            }
           // break;
        }
        System.out.println("插入成功数：" + insert_correct + "，插入失败数：" + insert_error);
        //其次根据出版源id(publication_id)，结合成果信息result_name,result_desc,result_type=1,result_time插入成果表
        //然后根据相关作者，将合著作者信息，先插入expert表（这里只有专家名），在将 专家id 和 成果id 插入专家成果表exp_res
        return null;
    }

    @Override
    public String insertPatent(List<Map<String, Object>> list) throws Exception {


        System.out.println("list.size()"+list.size());
        for (int i = 0; i < list.size(); i++){
            Map<String, Object> map = list.get(i);
            String result_name = (String) map.get("result_name");
            String result_desc = (String) map.get("result_desc");
            Integer result_type = (Integer) map.get("result_type");
            System.out.println(map.get("result_time"));
            String result_date = (String) map.get("result_time");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            Date result_time=sdf.parse(result_date);
            String expertName=(String)map.get("author");
            System.out.println(expertName);

            InsertResultUtil insertResultUtil=new InsertResultUtil(userMapper,resultMapper,publicationMapper,
                    expertMapper,expResMapper);
            insertResultUtil.insertPatentOne(result_name, result_desc,  result_type,  result_time,  expertName);
            // break;
        }
        return null;
    }
}
