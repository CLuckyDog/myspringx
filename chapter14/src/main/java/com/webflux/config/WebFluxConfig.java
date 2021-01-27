package com.webflux.config;

import com.webflux.enumeration.SexEnum;
import com.webflux.pojo.User;
import com.webflux.validator.UserValidator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/19 16:49
 * @description:
 * @modified By:
 */
/*实现java8的接口*/
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
    /*注册Converter*/

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUserConverter());
    }

    /*
    * 定义String -->  User类型转换器
    * @Bean  如果用上这个注解，Spring Bean，Spring Boot会自动识别为类型转换器
    * */
    public Converter<String, User> stringToUserConverter(){
        Converter<String, User> converter = new Converter<String, User>() {
            @Override
            public User convert(String s) {
                String strArr[] = s.split("-");
                User user = new User();
                String id = strArr[0];
                user.setId(id);
                user.setUserName(strArr[1]);
                int sexCode = Integer.valueOf(strArr[2]);
                SexEnum sex = SexEnum.getSexEnum(sexCode);
                user.setSex(sex.getName());
                user.setNote(strArr[3]);
                return user;
            }
        };
        return converter;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/static/**") /*注册资源，可以通过URI访问*/
                .addResourceLocations("/public","classpath:/static/")   /*注册Spring资源，可以再Spring机制中访问*/
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));  /*缓存一年365天*/
    }

    //    /*设置全局性验证器*/
//    @Override
//    public Validator getValidator() {
//        return new UserValidator();
//    }
}
