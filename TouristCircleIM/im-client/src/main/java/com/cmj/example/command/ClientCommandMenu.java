package com.cmj.example.command;

import java.util.Scanner;

/**
 * @author mengjie_chen
 * @description date 2020/10/9
 */
public class ClientCommandMenu implements BaseCommand{
    public static final String KEY = "0";
    public static final String TIP = "show 所有命令";

    private String allCommandShow;
    private String commandInput;

    /*<---------------------------gettter() && setter---------------------------->*/

    public String getAllCommandShow() {
        return allCommandShow;
    }

    public void setAllCommandShow(String allCommandShow) {
        this.allCommandShow = allCommandShow;
    }

    public String getCommandInput() {
        return commandInput;
    }

    public void setCommandInput(String commandInput) {
        this.commandInput = commandInput;
    }

    /*<---------------------------gettter() && setter---------------------------->*/

    @Override
    public void exec(Scanner scanner) {
        System.out.println("请输入某个操作指令：");
        System.out.println(allCommandShow);
        commandInput = scanner.next();
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
