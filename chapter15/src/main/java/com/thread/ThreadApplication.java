package com.thread;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/20 15:36
 * @description:
 * @modified By:
 */
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.thread")
@RestController
// 定义扫描MyBatis接口
@MapperScan(annotationClass = Mapper.class, basePackages = "com.thread.dao")
@EnableScheduling
public class ThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "chapter15 hello!";
    }
}
