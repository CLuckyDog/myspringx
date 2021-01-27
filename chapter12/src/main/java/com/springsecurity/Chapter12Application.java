package com.springsecurity;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/6 16:52
 * @description:
 * @modified By:
 */
@SpringBootApplication(scanBasePackages = "com.springsecurity")
@MapperScan(basePackages="com.springsecurity.dao", annotationClass = Mapper.class)
public class Chapter12Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter12Application.class, args);
    }
}
