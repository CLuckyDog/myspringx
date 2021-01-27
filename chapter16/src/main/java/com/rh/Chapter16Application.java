package com.rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/22 11:37
 * @description:
 * @modified By:
 */
@SpringBootApplication
@RestController
public class Chapter16Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter16Application.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello chapter16";
    }
}
