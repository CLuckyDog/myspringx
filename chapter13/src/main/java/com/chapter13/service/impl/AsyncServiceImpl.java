package com.chapter13.service.impl;

import com.chapter13.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/13 17:11
 * @description:
 * @modified By:
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
    @Override
    @Async /*声明使用异步调用*/
    public void generateReport() {
        logger.info("-----报表线程名称：{}-----", Thread.currentThread().getName());
    }
}
