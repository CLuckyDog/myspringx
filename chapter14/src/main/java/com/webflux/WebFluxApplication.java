package com.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/15 16:12
 * @description:
 * @modified By:
 */
@SpringBootApplication(scanBasePackages = "com.webflux")
/*
* 因为引入JPA，所以默认情况下，需要配置数据源
* 通过@EnableAutoConfiguration 排除原有自动配置的数据源
* */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// 在WebFlux下，驱动MongoDB的JPA接口
@EnableReactiveMongoRepositories(
        // 定义扫描的包
        basePackages="com.webflux.repository")
@RestController
public class WebFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello webflux!";
    }
}
