package com.chapter13.controller;

import com.chapter13.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/13 17:19
 * @description:
 * @modified By:
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncService service;

    @GetMapping("/page")
    public String asyncPage(){
        logger.info("--------请求线程名称：{}-------",Thread.currentThread().getName());
        /*调用异步服务*/
        service.generateReport();
        return "async";
    }
}
