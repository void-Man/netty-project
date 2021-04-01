package com.cmj.example.handle;

import com.cmj.example.component.ServerSession;
import com.cmj.example.processor.ChatProcesser;
import com.cmj.example.utils.common.CallbackTask;
import com.cmj.example.utils.common.CallbackTaskScheduler;
import com.cmj.example.vo.ChatMessageVo;
import com.cmj.example.vo.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description 登录处理器
 * @date 2020/10/8
 */
public class ChatRequestHandler extends ChannelInboundHandlerAdapter {

    private final ChatProcesser chatProcesser = new ChatProcesser();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 非Message类型  放行
        if (Objects.isNull(msg) || !(msg instanceof ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        ProtoMsg.Message message = (ProtoMsg.Message) msg;
        // 非聊天类型，放行
        if (!message.getType().equals(chatProcesser.headType())) {
            super.channelRead(ctx, msg);
            return;
        }

        // 获取session
        ServerSession serverSession = ctx.channel().attr(ServerSession.SESSION_KEY).get();
        // 设置chatvo
        serverSession.setChatMessageVo(ChatMessageVo.proto2ChatMessageVo(message.getMessageRequest()));

        // 调用异步任务，处理聊天转发逻辑
        CallbackTaskScheduler.add(new CallbackTask<Boolean>() {
            @Override
            public Boolean execute() throws Exception {
                return chatProcesser.action(serverSession, message);
            }

            @Override
            public void onBack(Boolean aBoolean) {
                if (aBoolean) {
                    System.out.println("发送消息成功：" + serverSession.getChatMessageVo().getContent());
                } else {
                    System.out.println("发送消息失败");
                }
            }

            @Override
            public void onException(Throwable t) {
                serverSession.closeSession(ctx);
                System.out.println("发送消息失败");
            }
        });
    }
}
