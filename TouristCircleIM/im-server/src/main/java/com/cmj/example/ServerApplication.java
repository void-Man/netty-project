package com.cmj.example;

/**
 * @author mengjie_chen
 * @description 服务器启动类
 * @date 2020/10/8
 */
public class ServerApplication {

    public static void main(String[] args) {
        new NettyServer(8082).startServer();
    }

}
