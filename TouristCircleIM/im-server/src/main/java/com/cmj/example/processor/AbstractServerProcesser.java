package com.cmj.example.processor;

import com.cmj.example.component.ServerSession;
import io.netty.channel.Channel;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/8
 */
public abstract class AbstractServerProcesser implements ServerProcesser {

    protected String getKey(Channel channel) {
        return channel.attr(ServerSession.KEY_USER_ID).get();
    }

    protected void setKey(Channel channel, String key) {
        channel.attr(ServerSession.KEY_USER_ID).set(key);
    }

    protected void checkAuth(Channel channel) throws Exception {
        if (Objects.isNull(this.getKey(channel))) {
            throw new Exception("此用户，没有登录成功");
        }
    }

}
