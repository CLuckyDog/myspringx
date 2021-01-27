package com.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/11 14:52
 * @description:
 * @modified By:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*重写映射 关系*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*使得/login/page映射为login.jsp*/
        registry.addViewController("/login/page").setViewName("login");
        /*使得/logout/page映射为logout_welcome.jsp*/
        registry.addViewController("/logout/page").setViewName("logout_welcome");
        /*使得/logout 映射为 logout.jsp*/
        registry.addViewController("/logout").setViewName("logout");
    }
}
