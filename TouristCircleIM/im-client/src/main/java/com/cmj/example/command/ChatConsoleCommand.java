package com.cmj.example.command;

import java.util.Scanner;

/**
 * @author mengjie_chen
 * @description date 2020/10/12
 */
public class ChatConsoleCommand implements BaseCommand {
    public static final String KEY = "2";
    public static final String TIP = "聊天";

    private String toUserId;
    private String content;

    /*<---------------------------gettter() && setter---------------------------->*/
    public String getToUserId() {
        return toUserId;
    }

    public String getContent() {
        return content;
    }
    /*<---------------------------gettter() && setter---------------------------->*/

    @Override
    public void exec(Scanner scanner) {
        System.out.println("请输入用户信息(id@content)  ");
        String next = scanner.next();
        while (next.split("@").length < 2) {
            System.out.println("输入信息有误，请重新输入");
            next = scanner.next();
        }
        toUserId = next.split("@")[0];
        content = next.split("@")[1];
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getTip() {
        return TIP;
    }
}
