package com.njust.utils;

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

        String txtStr = new ReadJsonUtil().getJson(path+"lqm_new.json");
//        System.out.println(txtStr.substring(4173445, 4173450));
        new ReadJsonUtil().getInfo(txtStr, 2);
    }
}
