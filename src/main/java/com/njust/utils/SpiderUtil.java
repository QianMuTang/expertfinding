package com.njust.utils;

import java.util.concurrent.FutureTask;

public class SpiderUtil {

    /**
     *
     * @param spider_path 爬虫路径
     * @param spider_name 爬虫名字
     * @param expert_name 专家名
     * @return 成功返回0
     * 命令执行错误，最多重复执行5次
     */
    public Integer spider(String spider_path, String data_path, String spider_name, String expert_name){
        int exitValue = -1;
        int proc_error_count = -1;
        while(exitValue != 0 && proc_error_count <= 5){
            proc_error_count++;
            try{
//                System.out.println("在Aminer爬取"+expert_name+"的个人信息");
                Process proc = Runtime.getRuntime().exec("python " + spider_path + spider_name + " " + expert_name + " " + data_path);
                exitValue = proc.waitFor();
            }catch (Exception e) {
                System.out.println("第" + proc_error_count + "次命令出错");
                e.printStackTrace();
                continue;
            }
        }
        return exitValue;
    }

    public Integer multiThreadSpider(String spider_path, String data_path, String spider_name, String expert_name){
        ThreadUtil aminerSpider = new ThreadUtil(spider_path, data_path, spider_name, expert_name);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(aminerSpider);
        new Thread(futureTask, "aminer").start();
        try{
            // 成功则输出标志0
            System.out.println(Thread.currentThread().getName() + "线程的返回值：" + futureTask.get());
            return futureTask.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
