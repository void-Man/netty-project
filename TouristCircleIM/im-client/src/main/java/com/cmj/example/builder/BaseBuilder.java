package com.cmj.example.builder;

import com.cmj.example.components.ClientSession;
import com.cmj.example.vo.ProtoMsg;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/8
 */
public class BaseBuilder {

    private final ProtoMsg.HeadType headType;
    private long seqId;
    private final ClientSession clientSession;

    public BaseBuilder(ProtoMsg.HeadType headType, ClientSession clientSession) {
        this.headType = headType;
        this.clientSession = clientSession;
    }

    public ProtoMsg.Message buildCommonMessage(long seqId) {
        this.seqId = seqId;
        return ProtoMsg.Message.newBuilder()
                .setType(headType)
                .setSequence(seqId)
                .setSessionId(clientSession.getSessionId())
                .buildPartial();
    }
}
