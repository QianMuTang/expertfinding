package com.njust.utils;

import com.njust.bean.baseBean.*;
import com.njust.dao.baseDao.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InsertResultUtil {

    private final UserMapper userMapper;

    private final ResultMapper resultMapper;

    private final PublicationMapper publicationMapper;

    private final ExpertMapper expertMapper;

    private final ExpResMapper expResMapper;

    public InsertResultUtil(UserMapper userMapper,ResultMapper resultMapper,PublicationMapper publicationMapper,
                            ExpertMapper expertMapper,ExpResMapper expResMapper){
        this.userMapper=userMapper;
        this.resultMapper=resultMapper;
        this.publicationMapper=publicationMapper;
        this.expertMapper=expertMapper;
        this.expResMapper=expResMapper;

    }

    @Transactional(rollbackFor = Exception.class)
    public   void  insertOne(String result_name, String result_desc, Integer result_type, Date result_time, String publication_name, String expertName){

        String expertName1=expertName.replace("[","").replace("]","").replaceAll("\"","");
        String[] str=expertName1.split(",");
        List expertIdList=new ArrayList();
        for(int i=0;i<str.length;i++){
            Expert expert=new Expert();
            expert.setExpertName(str[i]);
            Expert expert1=null;
            Integer expertId;
            try{
                System.out.println("专家:"+expert.getExpertName());
                expertMapper.selectOne(expert);
            }catch (Exception e){
                System.out.println("查询专家出错啦...................");
                e.printStackTrace();

            }
            System.out.println("lalalla"+expertMapper.selectOne(expert));
            // System.out.println("leixing:"+expertMapper.selectOne(expert).getClass().toString());
            if(expertMapper.selectOne(expert)==null || expertMapper.selectOne(expert).equals("")){
                expertMapper.insert(expert);
                Expert exp=expertMapper.selectOne(expert);
                expertId=exp.getExpertId();
            }else{
                expert1=expertMapper.selectOne(expert);
                expertId=expert1.getExpertId();
            }
            expertIdList.add(expertId);
        }


        Publication publication=new Publication();
        publication.setPublicationName(publication_name);
        System.out.println("publication_name"+publication_name);
        Publication pub= publicationMapper.selectOne(publication);
        Integer publication_id;
        if(pub==null){
            publicationMapper.insert(publication);
            Publication pub1= publicationMapper.selectOne(publication);
            publication_id=pub1.getPublicationId();
        }else{
            publication_id=pub.getPublicationId();
        }

        Result result=new Result();
        result.setResultName(result_name);
        if (result_desc.length()>4900){
            result_desc = result_desc.substring(0,4900);
        }
        result.setResultDesc(result_desc);
        result.setResultType(result_type);
        result.setResultTime(result_time);
        result.setPubilcationId(publication_id);
        if(resultMapper.selectOne(result)==null) {
            resultMapper.insert(result);
        }

        Result r1=resultMapper.selectOne(result);
        Long resultId=r1.getResultId();

        for(int j=0;j<expertIdList.size();j++){
            ExpRes expRes=new ExpRes();
            String expertId=String.valueOf(expertIdList.get(j));
            expRes.setExpertId(Integer.parseInt((expertId)));
            expRes.setResultId(resultId);
            expResMapper.insert(expRes);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void insertPatentOne(String result_name, String result_desc, Integer result_type, Date result_time,  String expertName){

        String expertName1=expertName.replace("[","").replace("]","").replaceAll("\"","");
        String[] str=expertName1.split(",");
        List expertIdList=new ArrayList();
        for(int i=0;i<str.length;i++){
            Expert expert=new Expert();
            expert.setExpertName(str[i]);
            Expert expert1=null;
            Integer expertId;
            try{
                System.out.println("专家:"+expert.getExpertName());
                expertMapper.selectOne(expert);
            }catch (Exception e){
                System.out.println("chucuola...................");
                e.printStackTrace();

            }
            System.out.println("lalalla"+expertMapper.selectOne(expert));
            // System.out.println("leixing:"+expertMapper.selectOne(expert).getClass().toString());
            if(expertMapper.selectOne(expert)==null || expertMapper.selectOne(expert).equals("")){
                expertMapper.insert(expert);
                Expert exp=expertMapper.selectOne(expert);
                expertId=exp.getExpertId();
            }else{
                expert1=expertMapper.selectOne(expert);
                expertId=expert1.getExpertId();
            }
            expertIdList.add(expertId);
        }



        Result result=new Result();
        result.setResultName(result_name);
        result.setResultDesc(result_desc);
        result.setResultType(result_type);
        result.setResultTime(result_time);
        if(resultMapper.selectOne(result)==null) {
            resultMapper.insert(result);
        }

        Result r1=resultMapper.selectOne(result);
        Long resultId=r1.getResultId();

        for(int j=0;j<expertIdList.size();j++){
            ExpRes expRes=new ExpRes();
            String expertId=String.valueOf(expertIdList.get(j));
            expRes.setExpertId(Integer.parseInt((expertId)));
            expRes.setResultId(resultId);
            expResMapper.insert(expRes);
        }

    }
}
