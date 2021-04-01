package com.cmj.example;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class ClientApplication {
    public static void main(String[] args) {
        CommandController commandController = new CommandController();
        commandController.InitCommand();
        commandController.startCommandThread();
    }
}
