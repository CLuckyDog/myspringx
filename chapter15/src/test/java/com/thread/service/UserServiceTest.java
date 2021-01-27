package com.thread.service;

import com.thread.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
/*测试restful接口的时候，使用随机端口测试服务*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser() throws Exception {
        Assert.assertEquals(Integer.valueOf(1),userService.addUser("zhihao.miao"));
        Assert.assertEquals(Integer.valueOf(0),userService.addUser(null));
    }

    /*REST测试模板，Spring Boot自动提供*/
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getUser(){
        /*请求当前启动的项目，请注意URI的缩写，没有指定IP和PORT*/
        User user = this.restTemplate.getForObject("/user/{id}", User.class, 1);
        System.out.println("user:"+user.getUserName());
        Assert.assertNotNull(user);
    }

}