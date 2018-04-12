package com.njust.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.FutureTask;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StartSpiderUtilTest {

    @Value("${web.spider-path}")
    private String spider_path;

    @Value("${web.data-path}")
    private String data_path;

    @Value("${web.aminer-spider}")
    private String spider_name;

    @Test
    public void startSpiderUtilTest(){
        String expert_name = "李千目";
        System.out.println("爬虫名"+spider_name);
        System.out.println(new SpiderUtil().multiThreadSpider(spider_path, data_path, spider_name, expert_name));

//        System.out.println(new SpiderUtil().spider(spider_path, spider_name, expert_name));
    }
}
