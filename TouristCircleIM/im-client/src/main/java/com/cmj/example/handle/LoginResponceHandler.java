package com.cmj.example.handle;

import com.cmj.example.components.ClientSession;
import com.cmj.example.vo.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description 登录响应处理器
 * date 2020/10/9
 */
public class LoginResponceHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 非ProtoMsg.Message类型，放行
        if (Objects.isNull(msg) || !(msg instanceof ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        // 非LoginResponse类型，放行
        ProtoMsg.Message message = (ProtoMsg.Message) msg;
        if (!message.getType().equals(ProtoMsg.HeadType.LOGIN_RESPONSE)) {
            super.channelRead(ctx, msg);
            return;
        }

        // 处理登录返回信息，截断流水线
        ProtoMsg.LoginResponse loginResponse = message.getLoginResponse();
        if (loginResponse.getIsSuccess()) {
            ctx.channel().attr(ClientSession.CLIENT_SESSION_KEY).get().loginSuccess();
            System.out.println("登录成功");
            // 在编码器后面动态插入心跳处理器
            ctx.pipeline().addAfter("encoder", "heartBeats", new HeartBeatsClientHandler());
            // 移除登录响应处理器
            ctx.pipeline().remove(this);
        } else {
            System.out.println("登录失败：" + loginResponse.getDesc());
        }
    }
}
