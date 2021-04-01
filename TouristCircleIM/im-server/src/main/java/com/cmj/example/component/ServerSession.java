package com.cmj.example.component;

import com.cmj.example.vo.ChatMessageVo;
import com.cmj.example.vo.HeartBeatsVo;
import com.cmj.example.vo.UserVo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mengjie_chen
 * @description 服务器session
 * @date 2020/10/7
 */
public class ServerSession {

    public static final AttributeKey<String> KEY_USER_ID = AttributeKey.valueOf("key_user_id");
    public static final AttributeKey<ServerSession> SESSION_KEY = AttributeKey.valueOf("session_key");

    /**
     * netty通道
     */
    private final Channel channel;
    /**
     * 用户
     */
    private UserVo userVo;
    /**
     * 聊天信息vo
     */
    private ChatMessageVo chatMessageVo;
    /**
     * 心跳vo
     */
    private HeartBeatsVo heartBeatsVo;
    /**
     * sessionId
     */
    private final String sessionId;
    /**
     * sessionId
     */
    private boolean isLogin = false;
    /**
     * session中缓存的变量，这里使用ConcurrentHashMap是因为同一个用户可以多个端同时发送消息
     */
    private Map<String, Object> cache = new ConcurrentHashMap<>(8);

    public ServerSession(Channel channel) {
        this.channel = channel;
        this.sessionId = UUID.randomUUID().toString().replace("-", "");
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public ChatMessageVo getChatMessageVo() {
        return chatMessageVo;
    }

    public void setChatMessageVo(ChatMessageVo chatMessageVo) {
        this.chatMessageVo = chatMessageVo;
    }

    public HeartBeatsVo getHeartBeatsVo() {
        return heartBeatsVo;
    }

    public void setHeartBeatsVo(HeartBeatsVo heartBeatsVo) {
        this.heartBeatsVo = heartBeatsVo;
    }

    public String getSessionId() {
        return sessionId;
    }

    /**
     * 向session中缓存值
     *
     * @param key
     * @param value
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public void setAttribute(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * 从session中获取值
     *
     * @param key
     * @return T
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public <T> T get(String key) {
        return (T) cache.get(key);
    }

    public boolean isValid() {
        return Objects.nonNull(userVo);
    }

    /**
     * 反向导航，从通道中获取session
     *
     * @param context
     * @return com.cmj.example.server.component.ServerSession
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public ServerSession getSession(ChannelHandlerContext context) {
        Channel channel = context.channel();
        return channel.attr(SESSION_KEY).get();
    }

    /**
     * 关闭session
     *
     * @param context
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public void closeSession(ChannelHandlerContext context) {
        ServerSession serverSession = context.channel().attr(SESSION_KEY).get();
        if (Objects.nonNull(serverSession) && serverSession.isValid()) {
            serverSession.close();
            SessionMap.instance().removeSession(serverSession.getSessionId());
        }
    }

    /**
     * 和channel通道实现双向绑定
     *
     * @param
     * @return com.cmj.example.server.component.ServerSession
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public ServerSession bind() {
        System.out.println(" ServerSession 绑定会话 " + channel.remoteAddress());
        channel.attr(SESSION_KEY).set(this);
        SessionMap.instance().addSession(sessionId, this);
        isLogin = true;
        return this;
    }

    /**
     * 解绑
     *
     * @param
     * @return com.cmj.example.server.component.ServerSession
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public ServerSession unbind() {
        System.out.println(" ServerSession 解除绑定会话 " + channel.remoteAddress());
        isLogin = false;
        SessionMap.instance().removeSession(sessionId);
        this.close();
        return this;
    }

    /**
     * 写Protobuf数据帧
     * 这里使用synchronized的原因是同一时间可能会有多个向通道中的写入请求
     *
     * @param pkg
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public synchronized void writeAndFlush(Object pkg) {
        channel.writeAndFlush(pkg);
    }

    /**
     * 关闭连接
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    private synchronized void close() {
        channel.close().addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println("通道关闭成功");
            } else {
                System.out.println("通道关闭失败");
            }
        });

    }

}
