package com.cmj.example.handle;

import com.cmj.example.component.ServerSession;
import com.cmj.example.processor.HeartBeatsProcess;
import com.cmj.example.utils.common.FutureTaskScheduler;
import com.cmj.example.vo.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author mengjie_chen
 * @description 心跳检测处理器
 * date 2020/10/13
 */
public class HeartBeatServerHandler extends IdleStateHandler {
    private static final long READ_IDLE_GAP = 50L;
    private final HeartBeatsProcess process = new HeartBeatsProcess();

    public HeartBeatServerHandler() {
        super(READ_IDLE_GAP, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        // 超时未接收到心跳消息，关闭连接
        System.out.println(READ_IDLE_GAP + "秒未收到心跳，关闭连接");
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (Objects.isNull(msg) || !(msg instanceof ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }
        ProtoMsg.Message heartBeat = (ProtoMsg.Message) msg;
        if (!process.headType().equals(ProtoMsg.HeadType.HEART_BEAT)) {
            super.channelRead(ctx, msg);
            return;
        }

        FutureTaskScheduler.add(() -> process.action(ctx.channel().attr(ServerSession.SESSION_KEY).get(), heartBeat));
    }
}
