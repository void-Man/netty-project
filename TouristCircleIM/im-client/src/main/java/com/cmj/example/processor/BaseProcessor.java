package com.cmj.example.processor;

import com.cmj.example.vo.ProtoMsg;

public interface BaseProcessor {

    /**
     * 处理器类型
     * @author mengjie_chen
     * @date 2020/10/9
     * @param
     * @return com.cmj.example.vo.ProtoMsg.HeadType
     */
    ProtoMsg.HeadType headType();

    /**
     * 处理器业务逻辑
     * @author mengjie_chen
     * @date 2020/10/9
     * @param
     * @return void
     */
    void action();

}
