package com.cmj.example.command;

import java.util.Scanner;

/**
 * @author mengjie_chen
 * @description date 2020/10/9
 */
public interface BaseCommand {

    /**
     * 处理输入逻辑
     * @author mengjie_chen
     * @date 2020/10/9
     * @param scanner
     * @return void
     */
    void exec(Scanner scanner);

    String getKey();

    String getTip();
}
