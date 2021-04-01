package com.cmj.example.command;

import java.util.Scanner;

/**
 * @author mengjie_chen
 * @description date 2020/10/9
 */
public class LoginConsoleCommand implements BaseCommand {
    public static final String KEY = "1";
    public static final String TIP = "登录";

    private String userId;
    private String password;

    /*<---------------------------gettter() && setter---------------------------->*/

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    /*<---------------------------gettter() && setter---------------------------->*/

    @Override
    public void exec(Scanner scanner) {
        System.out.println("请输入用户信息(id@password)  ");
        String[] split = null;
        while (true) {
            split = scanner.next().split("@");
            if (split.length != 2) {
                System.out.println("请按照格式输入(id@password):");
            } else {
                break;
            }
        }
        userId = split[0];
        password = split[1];
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
