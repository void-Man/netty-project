package com.cmj.example;

import com.cmj.example.component.ProtoBufMessageDecoder;
import com.cmj.example.component.ProtoBufMessageEncoder;
import com.cmj.example.handle.ChatRequestHandler;
import com.cmj.example.handle.HeartBeatServerHandler;
import com.cmj.example.handle.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class NettyServer {

    private final int port;
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();
    private final EventLoopGroup parent = new NioEventLoopGroup(1);
    private final EventLoopGroup child = new NioEventLoopGroup();

    public NettyServer(int port) {
        this.port = port;
        serverBootstrap.group(parent, child);
    }

    public void startServer() {
        try {
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(port);
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtoBufMessageDecoder());
                    ch.pipeline().addLast(new ProtoBufMessageEncoder());
                    ch.pipeline().addLast(new LoginRequestHandler());
                    ch.pipeline().addLast(new ChatRequestHandler());
                    ch.pipeline().addLast(new HeartBeatServerHandler());
                }
            };
            serverBootstrap.childHandler(channelInitializer);
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("服务器启动成功");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            child.shutdownGracefully();
            parent.shutdownGracefully();
        }
    }
}
