package com.cmj.example.processor;

import com.cmj.example.component.ServerSession;
import com.cmj.example.vo.ProtoMsg;

public interface ServerProcesser {

    /**
     * 请求类型
     *
     * @param
     * @return com.cmj.example.vo.ProtoMsg.HeadType
     * @author mengjie_chen
     * @date 2020/10/8
     */
    ProtoMsg.HeadType headType();

    /**
     * 业务逻辑
     *
     * @param
     * @return com.cmj.example.vo.ProtoMsg.HeadType
     * @author mengjie_chen
     * @date 2020/10/8
     */
    boolean action(ServerSession serverSession, ProtoMsg.Message message);

}
