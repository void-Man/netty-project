package com.cmj.example.builder;

import com.cmj.example.utils.common.CommonUtils;
import com.cmj.example.vo.ProtoMsg;

/**
 * @author mengjie_chen
 * @description date 2020/10/13
 */
public class MessageHeartBeatsBuilder {

    public ProtoMsg.Message heartBeatsBuilder(int seqId, String sessionId, String content) {
        ProtoMsg.Message.Builder messageBuilder = ProtoMsg.Message.newBuilder()
                .setType(ProtoMsg.HeadType.MESSAGE_RESPONSE)
                .setSequence(seqId)
                .setSessionId(sessionId);

        ProtoMsg.MessageHeartBeat.Builder heartBeatsBuilder = ProtoMsg.MessageHeartBeat.newBuilder()
                .setSeq(seqId)
                .setUid(CommonUtils.randomUUID())
                .setJson(content);
        messageBuilder.setHeartBeat(heartBeatsBuilder.build());
        return messageBuilder.build();
    }

}
