package com.webflux.service;

import com.webflux.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/18 17:17
 * @description:
 * @modified By:
 */
public interface UserService {

    Mono<User> getUser(String id);

    Mono<User> insertUser(User user);

    Mono<User> updateUser(User user);

    Mono<Void> deleteUser(String id);

    Flux<User> findUsers(String userName, String note);
}
