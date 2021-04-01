package com.cmj.example;

import com.cmj.example.command.BaseCommand;
import com.cmj.example.command.ChatConsoleCommand;
import com.cmj.example.command.ClientCommandMenu;
import com.cmj.example.command.LoginConsoleCommand;
import com.cmj.example.components.ClientSession;
import com.cmj.example.constants.CommonConstants;
import com.cmj.example.processor.ChatRequestProcessor;
import com.cmj.example.processor.LoginRequestProcessor;
import com.cmj.example.utils.common.CommonUtils;
import com.cmj.example.utils.common.FutureTaskScheduler;
import com.cmj.example.utils.common.NettyDemoConfig;
import com.cmj.example.vo.ChatMessageVo;
import com.cmj.example.vo.UserVo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author mengjie_chen
 * @description 客户端控制类
 * date 2020/10/9
 */
public class CommandController {

    /**
     * 控制台管理map
     */
    private Map<String, BaseCommand> commandMap = new HashMap<>(8);
    private ClientCommandMenu clientCommandMenu = new ClientCommandMenu();
    private LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
    private ChatConsoleCommand chatConsoleCommand = new ChatConsoleCommand();
    /**
     * 连接服务器标识
     */
    private boolean connected = false;
    private ClientSession clientSession;
    private final NettyClient nettyClient = new NettyClient(NettyDemoConfig.SOCKET_SERVER_IP, NettyDemoConfig.SOCKET_SERVER_PORT);

    /**
     * 服务器断开监听器(用于关闭连接)
     */
    private GenericFutureListener<ChannelFuture> closeListener = future -> {
        EventLoop eventLoop = future.channel().eventLoop();
        if (future.isSuccess()) {
            // 服务器连接成功
            System.out.println("通道关闭成功");
            this.closeChannel(future.channel());
            this.connected = false;
            this.notifyCommandThread();
        } else {
            System.out.println("连接服务器失败，10秒后重试");
            eventLoop.schedule(() -> {
                this.closeChannel(future.channel());
            }, 10, TimeUnit.SECONDS);
        }
    };

    /**
     * 服务器连接监听器(连接成功后调用，用于改变connected标识)
     */
    private GenericFutureListener<ChannelFuture> connectListener = future -> {
        EventLoop eventLoop = future.channel().eventLoop();
        if (future.isSuccess()) {
            // 服务器连接成功
            this.connected = true;
            clientSession = new ClientSession(future.channel());
            // 通道绑定close监听事件
            future.channel().closeFuture().addListener(closeListener);
            this.notifyCommandThread();
        } else {
            System.out.println("连接服务器失败，10秒后重试");
            eventLoop.schedule(nettyClient::startClient, 10, TimeUnit.SECONDS);
        }
    };


    /*<---------------------------gettter() && setter---------------------------->*/

    public ClientSession getClientSession() {
        return clientSession;
    }

    /*<---------------------------gettter() && setter---------------------------->*/

    /**
     * 调用session关闭通道
     *
     * @param channel
     * @return void
     * @author mengjie_chen
     * @date 2020/10/9
     */
    private void closeChannel(Channel channel) {
        ClientSession clientSession = channel.attr(ClientSession.CLIENT_SESSION_KEY).get();
        clientSession.close();
    }

    /**
     * 唤醒命令收集线程
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/9
     */
    private synchronized void notifyCommandThread() {
        this.notify();
    }

    /**
     * 暂停命令收集线程
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/9
     */
    private synchronized void waitCommandThread() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化命令界面
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/9
     */
    public void InitCommand() {
        commandMap.put(clientCommandMenu.getKey(), clientCommandMenu);
        commandMap.put(loginConsoleCommand.getKey(), loginConsoleCommand);
        commandMap.put(chatConsoleCommand.getKey(), chatConsoleCommand);

        StringBuilder menus = new StringBuilder();
        menus.append("[menu] ");
        for (BaseCommand value : commandMap.values()) {
            menus.append(value.getKey())
                    .append("->")
                    .append(value.getTip())
                    .append(" | ");
        }
        clientCommandMenu.setAllCommandShow(menus.toString());
    }

    /**
     * 连接服务器
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/9
     */
    private void connectServer() {
        FutureTaskScheduler.add(() -> {
            nettyClient.setConnectListener(connectListener);
            nettyClient.startClient();
        });
    }

    public void startCommandThread() {
        Thread.currentThread().setName("命令线程");
        while (true) {
            // 轮询直到连接到服务器
            while (connected == false) {
                this.connectServer();
                this.waitCommandThread();
            }

            Scanner scanner = new Scanner(System.in);
            clientCommandMenu.exec(scanner);
            BaseCommand baseCommand = commandMap.get(clientCommandMenu.getCommandInput());
            if (Objects.isNull(baseCommand)) {
                System.out.println("无法识别[" + clientCommandMenu.getCommandInput() + "]指令，请重新输入!");
                continue;
            }
            switch (clientCommandMenu.getCommandInput()) {
                case LoginConsoleCommand.KEY: {
                    LoginConsoleCommand loginConsoleCommand = (LoginConsoleCommand) baseCommand;
                    baseCommand.exec(scanner);
                    UserVo userVo = new UserVo();
                    userVo.setUserId(loginConsoleCommand.getUserId());
                    userVo.setToken(loginConsoleCommand.getPassword());
                    userVo.setDeviceId(CommonUtils.randomUUID());
                    userVo.setPlatform(CommonConstants.Platform.WINDOWS.getCode());
                    clientSession.setUserVo(userVo);
                    new LoginRequestProcessor(clientSession).action();
                    break;
                }
                case ChatConsoleCommand.KEY: {
                    ChatConsoleCommand chatConsoleCommand = (ChatConsoleCommand) baseCommand;
                    baseCommand.exec(scanner);
                    ChatMessageVo chatMessageVo = new ChatMessageVo();
                    chatMessageVo.setToUserId(chatConsoleCommand.getToUserId());
                    chatMessageVo.setContent(chatConsoleCommand.getContent());
                    chatMessageVo.setFromUserId(clientSession.getUserVo().getUserId());
                    chatMessageVo.setMessageType(CommonConstants.MessageType.TEXT.getCode());
                    chatMessageVo.setMessageId(System.currentTimeMillis());
                    chatMessageVo.setSendTime(System.currentTimeMillis());
                    clientSession.setChatMessageVo(chatMessageVo);
                    new ChatRequestProcessor(clientSession).action();
                    break;
                }
            }
        }

    }

}
