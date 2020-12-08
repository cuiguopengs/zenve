package com.zenve.chat.config;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/ws/{name}")
public class WsServerEndpoint {


    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger online = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static Map<String, Session> sessionPools = new HashMap<>();

    /**
     * 发送消息方法
     * @param session 客户端与socket建立的会话
     * @param message 消息
     * @throws IOException
     */
    public void sendMessage(Session session, String message) throws IOException{
        if(session != null){
            session.getBasicRemote().sendText(message);
        }
    }


    /**
     * 连接建立成功调用
     * @param session 客户端与socket建立的会话
     * @param userName 客户端的userName
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String userName){
        sessionPools.put(userName, session);
        addOnlineCount();
        System.out.println(userName + "加入webSocket！当前人数为" + online);
        try {
            sendMessage(session, "欢迎" + userName + "加入连接！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接时调用
     * @param userName 关闭连接的客户端的姓名
     */
    @OnClose
    public void onClose(@PathParam(value = "name") String userName){
        sessionPools.remove(userName);
        subOnlineCount();
        System.out.println(userName + "断开webSocket连接！当前人数为" + online);
    }

    /**
     * 收到客户端消息时触发（群发）
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session se, String message, @PathParam(value = "name") String userName) throws IOException{
        sendMessage(se,  "服务器已收到请求，正在转发");

        String[] s = message.split("_");
        String message1 = "";
        for (int i = s.length -1 ; i >= 0 ; i--) {
            if (i == s.length - 1) {
                message1 = s[i];
                continue;
            }
            Session session = sessionPools.get(s[i]);
            sendMessage(session, message1);
            sendMessage(se, "服务器消息已经成功发送给" + s[i]);
        }
//        for (Session session: sessionPools.values()) {
//            try {
//                System.out.println(userName + ": " + message);
//                sendMessage(session, message);
//            } catch(Exception e){
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    /**
     * 发生错误时候
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    /**
     * 给指定用户发送消息
     * @param userName 用户名
     * @param message 消息
     * @throws IOException
     */
    public void sendInfo(String userName, String message){
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addOnlineCount(){
        online.incrementAndGet();
    }

    public static void subOnlineCount() {
        online.decrementAndGet();
    }
}
