package com.webflux.validator;

import com.webflux.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/20 9:35
 * @description:
 * @modified By:
 */
public class UserValidator implements Validator {

    /*确定支持的验证类型*/
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    /*验证逻辑*/
    @Override
    public void validate(Object target, Errors errors) {
        User user= (User) target;
        /*检测用户名是否为空*/
        if(StringUtils.isEmpty(user.getUserName())){
                errors.rejectValue("userName",null,"用户名不能为空！");
        }
    }
}
