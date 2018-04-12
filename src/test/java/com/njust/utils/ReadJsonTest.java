package com.njust.utils;

import com.njust.bean.baseBean.Expert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReadJsonTest {

    @Value("${web.data-path}")
    private String path;

    /**
     * 获取json文件测试
     */
    @Test
    public void readJsonTest(){

        //解析成果信息
//        List<Map<String, Object>> list = new ArrayList<>();
//        list = ReadJsonUtil.parseResult(path+"lqm_all.json", 1);
//        for (int i = 0; i < 3; i++) {
//            System.out.println(list.get(i).toString());
//        }

        //解析专家信息
        Expert expert = ReadJsonUtil.parseExpert(path+"李千目_person_info.json");
        System.out.println("联系方式："+expert.getContact()+"主页地址："+expert.getHomepage()+"介绍："+expert.getIntroduce()+"标签："+expert.getTag());
    }
}
