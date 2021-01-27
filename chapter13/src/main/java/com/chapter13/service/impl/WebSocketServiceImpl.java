package com.chapter13.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/15 11:28
 * @description:
 * @modified By:
 */
@Service
@ServerEndpoint("/ws")
public class WebSocketServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);
    /*静态变量，用来记录当前在线连接数，应该把它设计成线程安全的*/
    private static int onlineCount=0;
    /*concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServiceImpl对象*/
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet=new CopyOnWriteArraySet<>();
    /*与某个客户端的连接回话，需要通过它来给客户端发送数据*/
    private Session session;

    /*连接建立成功时调用的方法*/
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketSet.add(this);
        addOnlineCount();
        logger.info("有连接加入！当前在线人数为：{}",getOnlineCount());

        try {
            sendMessage("有新的连接加入了！！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*连接关闭调用的方法*/
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        logger.info("有连接关闭！当前在线人数为：{}",getOnlineCount());
    }

    /*
    *  收到客户端消息后调用的方法
    *   @param message 客户端发送过来的消息
    * */
    @OnMessage
        public void onMessage(String message,Session session){
        logger.info("----------来自客户端【{}】的消息：{}----------",session.getId(),message);
        /*群发消息*/
        for (WebSocketServiceImpl item:webSocketSet){
            try {
                /*获取当前用户名*/
//            String userName=item.getSession().getUserPrincipal().getName;
//            logger.info(userName);
                item.sendMessage("客户端【"+session.getId()+"】:"+message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*发生错误时调用*/
    @OnError
    public void onError(Session session,Throwable error){
        logger.info("发生错误！");
        error.printStackTrace();
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /*返回在线人数*/
    private static synchronized int getOnlineCount(){
        return  onlineCount;
    }

    /*当连接人数增加时*/
    private static synchronized void addOnlineCount(){
        WebSocketServiceImpl.onlineCount++;
    }

    /*当连接人数减少时*/
    private static synchronized void subOnlineCount(){
        WebSocketServiceImpl.onlineCount--;
    }
}
