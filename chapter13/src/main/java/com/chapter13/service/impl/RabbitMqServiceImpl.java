package com.chapter13.service.impl;

import com.chapter13.pojo.User;
import com.chapter13.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/14 15:59
 * @description:
 * @modified By:
 */
/*实现ConfirmCallback接口，这样可以回调*/
@Service
public class RabbitMqServiceImpl implements RabbitMqService, RabbitTemplate.ConfirmCallback {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqServiceImpl.class);
    // 消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgRouting;

    // 用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userRouting;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*发送普通消息*/
    @Override
    public void sendMsg(String msg) {
        logger.info("------发送消息：{}-------",msg);
        /*设置回调*/
        rabbitTemplate.setConfirmCallback(this);
        /*发送消息，通过msgRouting确定队列*/
        rabbitTemplate.convertAndSend(msgRouting,msg);
    }

    /*发送用户对象消息*/
    @Override
    public void sendUser(User user) {
        logger.info("--------发送用户消息：{}-------",user);
        /*设置回调*/
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(userRouting,user);
    }

    /*回调确认方法*/
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            logger.info("------消息成功消费！-------");
        }else {
            logger.info("--------消息消费失败：{}-------",cause);
        }
    }
}
