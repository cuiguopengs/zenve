//package com.zenve.chat.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zenve.chat.entity.Group;
//import com.zenve.chat.vo.MessageVo;
//import com.zenve.chat.entity.User;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
//@Component
//@ServerEndpoint("/ws/{name}")
//public class WsServerEndpoint {
//
//
//    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static AtomicInteger online = new AtomicInteger();
//
//    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
//    private static Map<String, Session> sessionPools = new HashMap<>();
//
//    private static List<User> userSet = new ArrayList<User>(){{
//        User user = new User();
//        user.setUsername("cui");
//        user.setPassword("cui");
//        add(user);
//
//        User user1 = new User();
//        user1.setUsername("guo");
//        user1.setPassword("guo");
//        add(user1);
//
//        User user2 = new User();
//        user2.setUsername("peng");
//        user2.setPassword("peng");
//        add(user2);
//    }};
//    private static Set<User> userOnlineSet = new HashSet<>();
//
//    private Set<Group> groupSet = new HashSet();
//
//    /**
//     * 发送消息方法
//     * @param session 客户端与socket建立的会话
//     * @param message 消息
//     * @throws IOException
//     */
//    public void sendMessage(Session session, String message) throws IOException{
//        if(session != null){
//            session.getBasicRemote().sendText(message);
//        }
//    }
//
//    public void sendBinary(Session session, ByteBuffer byteBuffer) throws IOException{
//        if(session != null){
//            session.getBasicRemote().sendBinary(byteBuffer);
//        }
//    }
//
//    /**
//     * 连接建立成功调用
//     * @param session 客户端与socket建立的会话
//     * @param userName 客户端的userName
//     */
//    @OnOpen
//    public void onOpen(Session session, @PathParam(value = "name") String userName){
//        sessionPools.put(userName, session);
//        addOnlineCount();
//        System.out.println(userName + "加入webSocket！当前人数为" + online);
//        try {
//            sendMessage(session, "欢迎" + userName + "加入连接！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 关闭连接时调用
//     * @param userName 关闭连接的客户端的姓名
//     */
//    @OnClose
//    public void onClose(@PathParam(value = "name") String userName){
//        sessionPools.remove(userName);
//        subOnlineCount();
//        System.out.println(userName + "断开webSocket连接！当前人数为" + online);
//    }
//
//    /**
//     * 收到客户端消息时触发（群发）
//     * @param message
//     * @throws IOException
//     */
//    @OnMessage
//    public void onMessage(Session currentSession, String message, @PathParam(value = "name") String userName) throws Exception{
//        MessageVo messageVo = null;
//        try {
//            messageVo = new ObjectMapper().readValue(message, MessageVo.class);
//        } catch (Exception e) {
//        }
//        if (messageVo == null ) {
//            String[] s = message.split(" ");
//            String username = s[1];
//            String password = s[2];
//            User user = login(username, password);
//            if (null != user) {
//                sendMessage(currentSession, new ObjectMapper().writeValueAsString(user));
//            } else {
//                System.out.println("登录失败");
//            }
//        } else {
//            Group group = messageVo.getGroup();
//            String username = messageVo.getUsername();
//            List<User> collect = group.getUsers().stream().filter(user -> !user.getUsername().equals(username)).collect(Collectors.toList());
//            MessageVo finalMessageVo = messageVo;
//            collect.forEach(user -> {
//                Session session = sessionPools.get(user.getUsername());
//                if (null != session) {
//                    try {
//                        sendMessage(session, finalMessageVo.getMessage());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//    }
//
//    private User login(String username, String password) {
//        Set<User> collect = userSet.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).collect(Collectors.toSet());
//        if (collect.isEmpty()) {
//            return null;
//        } else {
//            User next = collect.iterator().next();
//            userOnlineSet.add(next);
//            return next;
//        }
//    }
//
//    /**
//     * 发生错误时候
//     * @param session
//     * @param throwable
//     */
//    @OnError
//    public void onError(Session session, Throwable throwable){
//        System.out.println("发生错误");
//        throwable.printStackTrace();
//    }
//
//    /**
//     * 给指定用户发送消息
//     * @param userName 用户名
//     * @param message 消息
//     * @throws IOException
//     */
//    public void sendInfo(String userName, String message){
//        Session session = sessionPools.get(userName);
//        try {
//            sendMessage(session, message);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void addOnlineCount(){
//        online.incrementAndGet();
//    }
//
//    public static void subOnlineCount() {
//        online.decrementAndGet();
//    }
//}
