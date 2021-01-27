package com.chapter13.service.impl;

import com.chapter13.service.ActiveMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/14 14:16
 * @description:
 * @modified By:
 */
@Service
public class ActiveMqServiceImpl  implements ActiveMqService {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMqServiceImpl.class);

    /*注入由springboot自动生成的jmsTemplate*/
    @Autowired
    private JmsTemplate jmsTemplate;

//    @Value("${spring.jms.template.default-destination}")
    private String defaultDestination = null;

    @Override
    public void sendMsg(String message) {
        logger.info("-------发送消息：{}------",message);
        jmsTemplate.convertAndSend(message);
//        /*自定义发送地址*/
//        jmsTemplate.convertAndSend("your address",message);
    }

    /*使用注解，监听地址发送过来的消息*/
    @Override
//    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        logger.info("-------接收到的消息：{}-------",message);
    }
}
