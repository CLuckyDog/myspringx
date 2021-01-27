package com.chapter13;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/12 17:42
 * @description:
 * @modified By:
 */
@SpringBootApplication(scanBasePackages="com.chapter13")
@MapperScan(basePackages="com.chapter13.dao", annotationClass = Mapper.class)
@RestController
public class Chapter13Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter13Application.class, args);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello chapter13!";
    }
}
