package com.cmj.example;

import com.cmj.example.utils.common.NettyDemoConfig;

/**
 * @author mengjie_chen
 * @description 服务器启动类
 * @date 2020/10/8
 */
public class ServerApplication {

    public static void main(String[] args) {
        new NettyServer(NettyDemoConfig.SOCKET_SERVER_PORT).startServer();
    }

}
