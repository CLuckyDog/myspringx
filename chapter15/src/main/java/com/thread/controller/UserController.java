package com.thread.controller;

import com.thread.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/22 15:35
 * @description:
 * @modified By:
 */
@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public Object getUsers(@PathVariable String id){
        User user = new User();
        user.setId(id);
        user.setUserName("test_user");
        user.setNote("test_note");
        user.setSex("未知！");
        return user;
    }

}
