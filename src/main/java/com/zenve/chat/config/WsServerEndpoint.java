package com.zenve.chat.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenve.chat.entity.Group;
import com.zenve.chat.entity.UserGroup;
import com.zenve.chat.service.GroupService;
import com.zenve.chat.service.UserGroupService;
import com.zenve.chat.service.UserService;
import com.zenve.chat.vo.LoginVo;
import com.zenve.chat.vo.MessageBus;
import com.zenve.chat.vo.MessageVo;
import com.zenve.chat.entity.User;
import com.zenve.chat.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@ServerEndpoint("/ws/{name}")
public class WsServerEndpoint {


    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserGroupService userGroupService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger online = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static Map<String, Session> sessionPools = new ConcurrentHashMap<>();

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

    public void sendBinary(Session session, ByteBuffer byteBuffer) throws IOException{
        if(session != null){
            session.getBasicRemote().sendBinary(byteBuffer);
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
     * 收到客户端消息时触发
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session currentSession, String message, @PathParam(value = "name") String userName) throws Exception{
        MessageBus messageBus = null;
        try {
            messageBus = new ObjectMapper().readValue(message, MessageBus.class);
        } catch (Exception e) {
        }
       /* if (messageBus.getClassName().equals(LoginVo.class.getName())) {
            LoginVo loginVo = (LoginVo) messageBus.getObject();
            UserVo user = userService.login(loginVo);
            if (null != user) {
                String suc = getMessageBusStr(User.class, user, "suc");
                sendMessage(currentSession, suc);
            } else {
                System.out.println("登录失败");
            }
        } else*/ if (messageBus.getClassName().equals(MessageVo.class.getName()) ) {
            dealChatMessage((MessageVo) messageBus.getObject());
        }
    }

    private void dealChatMessage(MessageVo messageVo) {
        Group group = messageVo.getGroup();
        List<UserGroup> userGroups = userGroupService.getAllByGroupId(group.getId());
        userGroups.forEach(ug -> {
            User user = userService.getOneById(ug.getUserId());
            Session session = sessionPools.get(user.getUsername());
            if (null != session) {
                try {
                    sendMessage(session, getMessageBusStr(MessageVo.class, messageVo,"suc"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    private String getMessageBusStr(Class clss, Object obj, String status) {
        MessageBus messageBus = new MessageBus();
        messageBus.setClassName(clss.getName());
        messageBus.setObject(obj);
        messageBus.setStatus(status);
        String s = null;
        try {
            s = new ObjectMapper().writeValueAsString(messageBus);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
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
