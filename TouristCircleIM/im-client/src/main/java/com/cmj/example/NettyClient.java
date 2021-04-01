package com.cmj.example;

import com.cmj.example.component.ProtoBufMessageDecoder;
import com.cmj.example.component.ProtoBufMessageEncoder;
import com.cmj.example.handle.ChatResponceHandler;
import com.cmj.example.handle.LoginResponceHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class NettyClient {
    private final Bootstrap bootstrap = new Bootstrap();
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private final String ip;
    private final int port;
    private GenericFutureListener connectListener;

    public NettyClient(String ip, int port) {
        bootstrap.group(eventLoopGroup);
        this.port = port;
        this.ip = ip;
    }

    public void setConnectListener(GenericFutureListener connectListener) {
        this.connectListener = connectListener;
    }

    public void startClient() {
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.remoteAddress(ip, port);

        ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("decoder", new ProtoBufMessageDecoder());
                ch.pipeline().addLast("encoder", new ProtoBufMessageEncoder());
                ch.pipeline().addLast(new LoginResponceHandler());
                ch.pipeline().addLast(new ChatResponceHandler());
            }
        };

        bootstrap.handler(channelInitializer);
        bootstrap.connect().addListener(connectListener);
    }

    public void close() {
        eventLoopGroup.shutdownGracefully();
    }
}
