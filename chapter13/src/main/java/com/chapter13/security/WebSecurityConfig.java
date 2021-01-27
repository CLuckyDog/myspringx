package com.chapter13.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/9 17:13
 * @description:
 * @modified By:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${system.my.password.secret}")
    private String secret;

    @Autowired
    private UserDetailsService userDetailsService;

    /*RAM signature service*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*password editor*/
        PasswordEncoder passwordEncoder=new Pbkdf2PasswordEncoder(secret);
        System.out.println("123456:"+passwordEncoder.encode("123456"));
        System.out.println("123456abc:"+passwordEncoder.encode("123456abc"));
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
