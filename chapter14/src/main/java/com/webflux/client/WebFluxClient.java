package com.webflux.client;

import com.sun.javafx.webkit.WebConsoleListener;
import com.sun.javafx.webkit.WebConsoleListener;
import com.webflux.client.pojo.UserPojo;
import com.webflux.pojo.User;
import com.webflux.vo.UserVo;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/19 14:39
 * @description:
 * @modified By:
 */
public class WebFluxClient {

    public static void main(String[] args) {
        /*创建WebClient对象，并且设置请求基础路径*/
        WebClient client=WebClient.create("http://localhost:8081");
//        /*一个新的用户*/
//        User newUser=new User();
//        newUser.setId("5ea2eb1daa59424af8d005ca");
//        newUser.setNote("new_note");
//        newUser.setUserName("user_name_new");
//        newUser.setSex("男");
//        /*新增用户*/
//        insertUser(client,newUser);

//        /*获取用户*/
//        getUser(client,"5ea3f176c3b58c12a8d870da");

//        User updUser=new User();
//        updUser.setId("aaaaaaaaaaaaaaaaaaaa");
//        updUser.setUserName("user_name_update1");
//        updUser.setNote("note_update1");
//        updUser.setSex("女");
//        /*更新用户*/
//        updateUser(client,updUser);

//        /*查询所有用户*/
//        findUsers(client,"user","note");

//        /*删除用户*/
//        deleteUser(client,"aaaaaaaaaaaaaaaaaaaa");

//        insertUserByConverter(client);

//        getUser2(client,"5ea3f176c3b58c12a8d22870da");
//        getUserPojo(client,"5ea3f176c3b58c12a8d870da");
        updUserName(client,"5ea3f176c3ssssb58c12a8d870da","aaaasssaa");

    }

    private static void insertUser(WebClient client , User newUser){
        /*定义post请求*/
        Mono<UserVo> userMono = client.post()
                .uri("/user")   /*设置请求URI*/
                .contentType(MediaType.APPLICATION_STREAM_JSON) /*请求体为JSON数据流*/
                .body(Mono.just(newUser), User.class)    /*请求体内容*/
                .accept(MediaType.APPLICATION_STREAM_JSON)  /*接收请求结果类型*/
                .retrieve()    /*设置请求结果检索规则*/
                .bodyToMono(UserVo.class);/*将结果体转换为一个Mono封装的数据流*/

        /*获取服务器发布的数据流，此时才会发送请求*/
        UserVo user=userMono.block();
        System.out.println("【用户名称】"+user.getUserName());

    }

    private static void getUser(WebClient client,String id){
        Mono<UserVo> userMono = client.get().uri("/user/{id}", id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);

        UserVo user=userMono.block();
        System.out.println("【用户名称】"+user.getUserName());
    }

    private static void updateUser(WebClient client,User updUser){
        Mono<UserVo> userMono = client.put().uri("/user")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(updUser), User.class)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);

        UserVo user=userMono.block();
        System.out.println("【用户名称】"+user.getUserName());
    }

    private static void findUsers(WebClient client,String userName,String note){
        Map<String,Object> map=new HashMap<>();
        map.put("userName",userName);
        map.put("note",note);

        Flux<UserVo> userFlux = client.get().uri("/user/{userName}/{note}", map)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(UserVo.class);

        /*通过Iterator遍历结果数据流，执行后服务器才会响应*/
        Iterator<UserVo> iterator = userFlux.toIterable().iterator();
        while (iterator.hasNext()){
            UserVo item=iterator.next();
            System.out.println("【用户名称】"+item.getUserName());
        }

    }

    private static void deleteUser(WebClient client,String id){
        Mono<Void> result = client.delete().uri("/user/{id}", id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(Void.class);

        Void voidResult=result.block();
        System.out.println("result:"+voidResult);
    }

    private static void insertUserByConverter(WebClient client){
        Mono<UserVo> userMono = client.post().uri("/converter/{user}", "bbbbbbbb-convert-0-note")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);

        UserVo user=userMono.block();
        System.out.println("【用户名称】"+user.getUserName());
    }
    // 转换方法
    private static UserPojo translate(UserVo vo) {
        if (vo == null) {
            return null;
        }
        UserPojo pojo = new UserPojo();
        pojo.setId(vo.getId());
        pojo.setUserName(vo.getUserName());
        // 性别转换
        pojo.setSex(vo.getSexCode() == 1 ? 1 : 2);
        pojo.setNote(vo.getNote());
        return pojo;
    }

    private static void getUser2(WebClient client,String id){
        Mono<UserVo> userMono = client.get().uri("/user/{id}", id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .onStatus(
                        /*
                         *   发生4开头或者5开头的状态码，4开头是客户端错误，5开头是服务器错误
                         *   第一个Lambda表达式，返回如果为true，则执行第二个Lambda表达式
                         * */
                        status->status.is4xxClientError()||status.is5xxServerError(),
                        /*
                         * 如果发生异常，则用第二个表达式返回作为结果
                         * 第二个Lambda表达式
                         * */
                        response->Mono.empty()
                )
                .bodyToMono(UserVo.class);

        UserVo user=userMono.block();
        if(user != null){
            System.out.println("【用户名称】"+user.getUserName());
        }else {
            System.out.println("服务器没有返回编号为："+id+"的用户！");
        }
    }
    private static void getUserPojo(WebClient client,String id){
        Mono<UserPojo> userMono = client.get()
                .uri("/user/{id}", id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()/*启用交换*/
                .doOnError(ex -> Mono.empty())/*出现错误返回空*/
                .flatMap(response -> response.bodyToMono(UserVo.class))/*获取服务器发送过来的UserVo对象*/
                .map(user -> translate(user));/*通过自定义方法转换为客户端的UserPojo*/

        UserPojo pojo=userMono.block();
        if (pojo!=null){
            System.out.println("【用户名称】"+pojo.getUserName());
        }else{
            System.out.println("服务器没有返回编号为："+id+"的用户！");
        }
    }
    private static void updUserName(WebClient client,String id,String userName){
        Mono<UserVo> userMono = client.put()
                .uri("/user/name", userName)
                .header("id", id)
                .header("userName", userName)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> Mono.empty()
                )
                .bodyToMono(UserVo.class);

        UserVo userVo=userMono.block();
        if (userVo!=null){
            System.out.println("【用户名称】"+userVo.getUserName());
        }else{
            System.out.println("服务器没有返回编号为："+id+"的用户！");
        }


    }



}
