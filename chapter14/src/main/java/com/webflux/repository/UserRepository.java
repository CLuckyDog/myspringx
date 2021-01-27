package com.webflux.repository;

import com.webflux.pojo.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/18 17:04
 * @description:
 * @modified By:
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {
    /**
    * @Author: pan_zhongjian
    * @Description: 对用户名和备注进行模糊查询
    * @DateTime: 2020/5/18 17:08
    * @Params: [userName  用户名称, note  备注]
    * @Return reactor.core.publisher.Flux<com.webflux.pojo.User>
    */
    Flux<User> findByUserNameLikeAndNoteLike(String userName,String note);
}
