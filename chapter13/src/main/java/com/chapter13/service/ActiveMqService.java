package com.chapter13.service;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/14 14:10
 * @description:
 * @modified By:
 */
/*ActiveMQ 服务接口*/
public interface ActiveMqService {
    /*发送消息*/
    void sendMsg(String message);
    /*接收消息*/
    void receiveMsg(String message);
}
