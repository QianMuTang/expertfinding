package com.njust.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadUtil implements Callable<Integer> {

    private String spider_path;
    private String data_path;
    private String spider_name;
    private String expert_name;

    public ThreadUtil(String spider_path, String data_path, String spider_name, String expert_name) {
        this.spider_path = spider_path;
        this.data_path = data_path;
        this.spider_name = spider_name;
        this.expert_name = expert_name;
    }

    @Override
    public Integer call() throws Exception {
        return new SpiderUtil().spider(spider_path, data_path, spider_name, expert_name);
    }
}
