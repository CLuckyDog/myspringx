package com.webflux.controller;

import com.webflux.pojo.User;
import com.webflux.service.UserService;
import com.webflux.validator.UserValidator;
import com.webflux.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/19 10:30
 * @description:
 * @modified By:
 */
@RestController
public class UserController {

        @Autowired
        private UserService userService;

        /*获取用户*/
        @GetMapping("/user/{id}")
        public Mono<UserVo> getUser(@PathVariable String id){
            return userService.getUser(id).map(u -> translate(u));
        }

        @PostMapping("/user")
        public Mono<UserVo> insertUser(@RequestBody User user){
            return userService.insertUser(user).map(u->translate(u));
        }

        @PutMapping("/user")
        public Mono<UserVo> updateUser(@RequestBody User user){
            return userService.updateUser(user).map(u -> translate(u));
        }

        @DeleteMapping("/user/{id}")
        public Mono<Void> deleteUser(@PathVariable String id){
            return userService.deleteUser(id);
        }

        @GetMapping("/user/{userName}/{note}")
        public Flux<UserVo> findUsers(@PathVariable String userName,@PathVariable String note){
            return userService.findUsers(userName,note).map(u -> translate(u));
        }

        @PostMapping("/converter/{user}")
        public Mono<UserVo> insertUserByConverter(@PathVariable("user") User user){
            return userService.insertUser(user).map(u -> translate(u));
        }

        @PostMapping("/validator")
        public Mono<UserVo> insertUserByValidator(@RequestBody @Valid User user){
            return userService.insertUser(user).map(u -> translate(u));
        }

        @PutMapping("/user/name")
        public Mono<UserVo> updUserName(@RequestHeader("id") String id,@RequestHeader("userName") String userName){
            Mono<User> userMono = userService.getUser(id);
            User user=userMono.block();
            if (user ==null){
                throw new RuntimeException("找不到用户信息！");
            }
            user.setUserName(userName);
            return this.updateUser(user);
        }

        private UserVo translate(User user){
            UserVo userVo=new UserVo();
            userVo.setUserName(user.getUserName());
            userVo.setSexCode("男".equals(user.getSex())?1:0);
            userVo.setSexName(user.getSex());
            userVo.setNote(user.getNote());
            userVo.setId(user.getId());
            return userVo;
        }
        /*加入局部验证器*/
        @InitBinder
        public void initBinder(DataBinder binder){
            binder.setValidator(new UserValidator());
        }
}
