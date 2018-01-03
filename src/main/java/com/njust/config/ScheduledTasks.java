package com.njust.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 创建定时任务
 */
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate=1000)
    public void reportCurrentTime() {
        System.out.println("简单的定时任务：此处运行爬虫.....");
    }

}
