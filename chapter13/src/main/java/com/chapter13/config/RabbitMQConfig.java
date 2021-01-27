package com.chapter13.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/14 15:55
 * @description:
 * @modified By:
 */
@Configuration
public class RabbitMQConfig {

    // 消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName = null;

    // 用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userQueueName = null;

    @Bean
    public Queue createQueueMsg() {
        // 创建字符串消息队列，boolean值代表是否持久化消息
        return new Queue(msgQueueName, true);
    }

    @Bean
    public Queue createQueueUser() {
        // 创建用户消息队列，boolean值代表是否持久化消息
        return new Queue(userQueueName, true);
    }
}
