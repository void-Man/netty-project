package com.cmj.example.components;

import com.cmj.example.vo.ChatMessageVo;
import com.cmj.example.vo.HeartBeatsVo;
import com.cmj.example.vo.UserVo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mengjie_chen
 * @description 客户端session
 * @date 2020/10/8
 */
public class ClientSession {

    public static final AttributeKey<ClientSession> CLIENT_SESSION_KEY = AttributeKey.valueOf("client_session_key");
    private final String sessionId;
    private final Channel channel;
    private final Map<String, Object> cache = new ConcurrentHashMap<>(8);
    private boolean isLogin = false;
    private UserVo userVo;
    private ChatMessageVo chatMessageVo;
    /**
     * 心跳vo
     */
    private HeartBeatsVo heartBeatsVo;

    public ClientSession(Channel channel) {
        this.sessionId = UUID.randomUUID().toString().replace("-", "");
        this.channel = channel;
        this.channel.attr(CLIENT_SESSION_KEY).set(this);
    }

    /*<---------------------------gettter() && setter---------------------------->*/

    public String getSessionId() {
        return sessionId;
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
    /*<---------------------------gettter() && setter---------------------------->*/

    public void loginSuccess() {
        this.isLogin = true;
    }

    public void set(String key, Object value) {
        cache.put(key, value);
    }

    public <R> R get(String key) {
        return (R) cache.get(key);
    }

    /**
     * 功能描述
     *
     * @param context
     * @return com.cmj.example.client.components.ClientSession
     * @author mengjie_chen
     * @date 2020/10/9
     */
    public ClientSession getSession(ChannelHandlerContext context) {
        return context.channel().attr(CLIENT_SESSION_KEY).get();
    }

    /**
     * 写数据
     *
     * @param pkg
     * @return void
     * @author mengjie_chen
     * @date 2020/10/9
     */
    public synchronized void writeAndFlush(Object pkg) {
        channel.writeAndFlush(pkg);
    }

    /**
     * 关闭通道
     *
     * @return void
     * @author mengjie_chen
     * @date 2020/10/9
     */
    public synchronized void close() {
        channel.close().addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                this.isLogin = false;
                System.out.println("通道关闭成功");
            } else {
                System.out.println("通道关闭失败");
            }
        });
    }
}
