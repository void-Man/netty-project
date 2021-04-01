package com.cmj.example.handle;

import com.cmj.example.component.ServerSession;
import com.cmj.example.processor.LoginProcesser;
import com.cmj.example.utils.common.CallbackTask;
import com.cmj.example.utils.common.CallbackTaskScheduler;
import com.cmj.example.vo.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description 登录处理器
 * @date 2020/10/8
 */
public class LoginRequestHandler extends ChannelInboundHandlerAdapter {

    private LoginProcesser loginProcesser = new LoginProcesser();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 非Message类型  放行
        if (Objects.isNull(msg) || !(msg instanceof ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        ProtoMsg.Message message = (ProtoMsg.Message) msg;
        // 非登录类型，放行
        if (!message.getType().equals(loginProcesser.headType())) {
            super.channelRead(ctx, msg);
            return;
        }

        //创建session
        ServerSession serverSession = new ServerSession(ctx.channel());

        // 调用异步任务，处理登录逻辑
        CallbackTaskScheduler.add(new CallbackTask<Boolean>() {
            @Override
            public Boolean execute() throws Exception {
                return loginProcesser.action(serverSession, message);
            }

            @Override
            public void onBack(Boolean aBoolean) {
                if (aBoolean) {
                    ctx.pipeline().remove(LoginRequestHandler.this);
                    System.out.println("登录成功" + serverSession.getUserVo());
                } else {
                    serverSession.closeSession(ctx);
                    System.out.println("登录失败" + serverSession.getUserVo());
                }
            }

            @Override
            public void onException(Throwable t) {
                serverSession.closeSession(ctx);
                System.out.println("登录失败" + serverSession.getUserVo());
            }
        });
    }
}
