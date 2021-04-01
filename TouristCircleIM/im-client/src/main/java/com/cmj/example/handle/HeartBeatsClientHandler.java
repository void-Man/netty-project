package com.cmj.example.handle;

import com.cmj.example.builder.HeartBeatsBuilder;
import com.cmj.example.components.ClientSession;
import com.cmj.example.vo.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author mengjie_chen
 * @description 消息响应处理器
 * date 2020/10/9
 */
public class HeartBeatsClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ClientSession clientSession = ctx.channel().attr(ClientSession.CLIENT_SESSION_KEY).get();
        HeartBeatsBuilder heartBeatsBuilder = new HeartBeatsBuilder(clientSession);
        ProtoMsg.Message message = heartBeatsBuilder.buildHeartBeats();
        heartBeats(ctx, message);
    }

    private void heartBeats(ChannelHandlerContext ctx, ProtoMsg.Message message) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(message);
                // 递归调用下一次发送心跳
                heartBeats(ctx, message);
            }
        }, 5, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 非ProtoMsg.Message类型，放行
        if (Objects.isNull(msg) || !(msg instanceof ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        ProtoMsg.Message message = (ProtoMsg.Message) msg;
        if (!message.getType().equals(ProtoMsg.HeadType.HEART_BEAT)) {
            super.channelRead(ctx, msg);
            return;
        }

        ProtoMsg.MessageHeartBeat heartBeat = message.getHeartBeat();
        System.out.println("收到的消息为：" + heartBeat.getJson());
    }
}
