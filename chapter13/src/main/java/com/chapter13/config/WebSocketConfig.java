package com.chapter13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/15 11:27
 * @description:
 * @modified By:
 */
/*使用STOMP协议，解决那些不支持websocket的浏览器可以使用websocket功能的问题*/
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    /*注册服务器端点*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*增加一个聊天服务端点*/
        registry.addEndpoint("/socket").withSockJS();
        /*增加一个用户服务端点*/
        registry.addEndpoint("/wsuser").withSockJS();
    }

    /*定义服务器端点请求和订阅前缀*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*客户端订阅路径前缀*/
        registry.enableSimpleBroker("/sub","/queue");
        /*服务端点请求前缀*/
        registry.setApplicationDestinationPrefixes("/request");
    }
}
