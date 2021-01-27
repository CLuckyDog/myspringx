package com.thread.service;

import org.springframework.stereotype.Service;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/22 14:50
 * @description:
 * @modified By:
 */
@Service
public class UserService {

    public Integer addUser(String username){
        System.out.println("user dao adduser [username="+username+"]");
        if(username == null){
            return 0;
        }
        return 1;
    }
}
