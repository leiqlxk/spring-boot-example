package org.lql.other.websocket.service;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Title: WebsocketServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 14:25 <br>
 */
@ServerEndpoint("/ws")
@Service
public class WebsocketServiceImpl {

    // 静态变量，用来记录当前在线连接数，应该把他设计成线程安全的
    private static int onlineCount = 0;
    // concurrent包的线程安全set，用来存放每个客户端对应的WebsocketServiceImpl对象
    private static CopyOnWriteArraySet<WebsocketServiceImpl> websocketServices = new CopyOnWriteArraySet<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 连接建立成功调用方法
    @OnOpen
    public void  onOpen(Session session) {
        this.session = session;
        websocketServices.add(this);

        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

        try {
            sendMessage("有新连接加入了！！");
        }catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    // 连接关闭调用的方法
    @OnClose
    public void onClose() {
        websocketServices.remove(this);
        subOnlineCount();
        System.out.println("有一个连接关闭！当前在线人数为" + getOnlineCount());
    }

    // 收到客户端消息后调用的方法
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端消息：" + message);

        // 群发消息
        for (WebsocketServiceImpl websocketService : websocketServices) {
            try {
                // 获取当前用户名称
                String userName = websocketService.getSession().getUserPrincipal().getName();
                System.out.println(userName);

                websocketService.sendMessage(message);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 发生错误时调用的方法
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    // 发送消息
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    // 返回在线人数
    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    // 当连接人数增加时
    private static synchronized void addOnlineCount() {
        WebsocketServiceImpl.onlineCount++;
    }

    // 当连接人数减少时
    private static synchronized void subOnlineCount() {
        WebsocketServiceImpl.onlineCount--;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
