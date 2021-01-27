package com.webflux.service.impl;

import com.webflux.pojo.User;
import com.webflux.repository.UserRepository;
import com.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/18 17:20
 * @description:
 * @modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> getUser(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        Mono<Void> result=userRepository.deleteById(id);
        return result;
    }

    @Override
    public Flux<User> findUsers(String userName, String note) {
        return userRepository.findByUserNameLikeAndNoteLike(userName,note);
    }
}
