package com.cmj.example;

import com.cmj.example.server.BootNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/8/15
 */
@SpringBootApplication
public class NettyApplication {

    public static void main( String[] args )
    {
        SpringApplication app = new SpringApplication(NettyApplication.class);
        app.run(args);
        // 启动  1883
        new BootNettyServer().startup();
    }

}
