package com.cmj.example.handle;

import com.cmj.example.vo.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description 消息响应处理器
 * date 2020/10/9
 */
public class ChatResponceHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 非ProtoMsg.Message类型，放行
        if (Objects.isNull(msg) || !(msg instanceof ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        // 非MessageResponse类型，放行
        ProtoMsg.Message message = (ProtoMsg.Message) msg;
        if (!message.getType().equals(ProtoMsg.HeadType.MESSAGE_RESPONSE)) {
            super.channelRead(ctx, msg);
            return;
        }

        // 处理登录返回信息，截断流水线
        ProtoMsg.MessageResponse messageResponse = message.getMessageResponse();
        System.out.println("收到的消息为：" + messageResponse.getDesc());
    }
}
