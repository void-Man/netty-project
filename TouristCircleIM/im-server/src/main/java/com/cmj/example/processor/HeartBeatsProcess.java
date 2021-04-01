package com.cmj.example.processor;

import com.cmj.example.builder.MessageHeartBeatsBuilder;
import com.cmj.example.component.ServerSession;
import com.cmj.example.vo.HeartBeatsVo;
import com.cmj.example.vo.ProtoMsg;

/**
 * @author mengjie_chen
 * @description date 2020/10/13
 */
public class HeartBeatsProcess extends AbstractServerProcesser {
    private final MessageHeartBeatsBuilder builder = new MessageHeartBeatsBuilder();

    @Override
    public ProtoMsg.HeadType headType() {
        return ProtoMsg.HeadType.HEART_BEAT;
    }

    @Override
    public boolean action(ServerSession serverSession, ProtoMsg.Message message) {
        HeartBeatsVo heartBeatsVo = HeartBeatsVo.proto2HeartBeatsVo(message.getHeartBeat());
        ProtoMsg.Message resMsg = builder.heartBeatsBuilder(heartBeatsVo.getSeqId(), serverSession.getSessionId(), heartBeatsVo.getContent());
        serverSession.writeAndFlush(resMsg);
        System.out.println("收到" + heartBeatsVo.getSeqId() + "心跳包");
        return true;
    }
}
