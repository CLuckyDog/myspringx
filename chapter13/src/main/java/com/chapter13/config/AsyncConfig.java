package com.chapter13.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/13 17:00
 * @description:
 * @modified By:
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    /*定义线程池*/
    @Override
    public Executor getAsyncExecutor() {
        /*定义线程池*/
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        /*核心线程数*/
        taskExecutor.setCorePoolSize(10);
        /*线程池最大线程数*/
        taskExecutor.setMaxPoolSize(30);
        /*线程队列最大线程数  意味着同时最多有2000个任务可以排队*/
        taskExecutor.setQueueCapacity(2000);
        /*初始化*/
        taskExecutor.initialize();
        return taskExecutor;
    }
}
