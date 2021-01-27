package com.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${system.my.password.secret}")
    private String secret;

//    @Autowired
//    private DataSource dataSource;
//
//    String pwdQuery="select user_name,pwd,available from t_user where user_name=?";
//    String roleQuery="select u.user_name,r.role_name from t_user u,t_user_role ur,t_role r where u.id=ur.user_id and r.id=ur.role_id and u.user_name=?";

    @Autowired
    private UserDetailsService userDetailsService;

    /*RAM signature service*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*password editor*/
//        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        PasswordEncoder passwordEncoder=new Pbkdf2PasswordEncoder(secret);

        System.out.println("123456:"+passwordEncoder.encode("123456"));
        System.out.println("123456abc:"+passwordEncoder.encode("123456abc"));
//        /*use RAM to storage*/
//        auth.inMemoryAuthentication()
//                /*set password editor*/
//            .passwordEncoder(passwordEncoder)
//                /*here registered a user that name is admin and its password is abc and give it USER ,ADMIN  role permissions */
//                .withUser("admin")
//                /*you can use passwordEncoder.encode("abc") to get the password */
//                .password("$2a$10$nDS/FixBH/TnIC6zEkad0erWPlrDZSQzb.6N8JmYBnHj9TJ8KqqHC")
//                .roles("USER","ADMIN")
//                .and()
//                .withUser("myuser")
//                .password("$2a$10$b36Cveh3516tphaXQ1YysuTGlVmgtKIIHcFHxGHeZXsD9drW8f97y")
//                .roles("USER");


//                /*the second method of implementation*/
//        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userConfig=auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder);
//        userConfig.withUser("admin")
//                .password("$2a$10$nDS/FixBH/TnIC6zEkad0erWPlrDZSQzb.6N8JmYBnHj9TJ8KqqHC")
//                .authorities("ROLE_USER","ROLE_ADMIN");
//        userConfig.withUser("myuser")
//                .password("$2a$10$b36Cveh3516tphaXQ1YysuTGlVmgtKIIHcFHxGHeZXsD9drW8f97y")
//                .authorities("ROLE_USER");

        /*use database to storage*/
//        auth.jdbcAuthentication()
//                .passwordEncoder(passwordEncoder)
//                .dataSource(dataSource)
//                .usersByUsernameQuery(pwdQuery)
//                .authoritiesByUsernameQuery(roleQuery);

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        /*限定签名后的权限  三种方式*/
        /*ANT风格*/
//        http
//                /*———第一段———*/
//                .authorizeRequests()
//                /*限定"/user/welcome"请求赋予角色ROLE_USER或者ROLE_ADMIN*/
//                .antMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
//                /*限定"/admin/"下所有的请求权限赋予角色ROLE_ADMIN*/
//                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//                /*其他路径允许签名后访问*/
//                .anyRequest().permitAll()
//                /*———第二段———*/
//                /*and 代表连接词*/
//                /*对于没有配置权限的其他请求允许匿名访问*/
//                .and().anonymous()
//                /*———第三段———*/
//                /*使用Spring security 默认的登录页面*/
//                .and().formLogin()
////                .and().formLogin().loginPage("/login/page").defaultSuccessUrl("/admin/welcome")
//                /*启动HTTP基础验证*/
//                .and().httpBasic();

//        /*正则式风格*/
//        http.authorizeRequests()
//                .regexMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
//                .regexMatchers("/admin/.*").hasAuthority("ROLE_ADMIN")
//                .and().formLogin()
//                .and().httpBasic();
//
//        /*Spring EL 方式*/
//        http.authorizeRequests()
//                /*使用Spring EL 表达式限定只有角色ROLE_USER或者ROLE_ADMIN*/
//                .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
//                /*设置访问权限给角色ROLE_ADMIN,要求是完整登录（非记住我登录）*/
//                .antMatchers("/admin/welcome").access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
//                /*使用记住我的功能*/
//                .and().rememberMe()
//                /*使用Spring security 默认的登录页面*/
//                .and().formLogin()
//                /*启动HTTP基础验证*/
//                .and().httpBasic();

        http.authorizeRequests()
                /*访问admin下的请求需要管理员权限*/
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                /*启用 remember me*/
                .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
                /*启用 HTTP Basic 功能*/
                .and().httpBasic()
                /*通过签名后可以访问任何请求*/
                .and().authorizeRequests().antMatchers("/**").permitAll()
                /*设置登录页和默认的跳转路径*/
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login/page").defaultSuccessUrl("/admin/welcome");
//                .and().formLogin().loginPage("/login/page").loginProcessingUrl("/admin/welcome");

//        http.formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/admin/welcome");
    }
}
