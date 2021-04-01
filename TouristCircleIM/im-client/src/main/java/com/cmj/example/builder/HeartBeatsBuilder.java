package com.cmj.example.builder;

import com.cmj.example.components.ClientSession;
import com.cmj.example.utils.common.CommonUtils;
import com.cmj.example.vo.ProtoMsg;

import static com.cmj.example.vo.ProtoMsg.HeadType.HEART_BEAT;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class HeartBeatsBuilder extends BaseBuilder {
    private static final ProtoMsg.HeadType headType = HEART_BEAT;
    private final ClientSession clientSession;

    public HeartBeatsBuilder(ClientSession clientSession) {
        super(HEART_BEAT, clientSession);
        this.clientSession = clientSession;
    }

    public ProtoMsg.Message buildHeartBeats() {
        ProtoMsg.Message commonMessage = super.buildCommonMessage(System.currentTimeMillis());
        ProtoMsg.MessageHeartBeat heartBeat = ProtoMsg.MessageHeartBeat.newBuilder()
                .setSeq(CommonUtils.randomLongId().intValue())
                .setJson(System.currentTimeMillis() + "心跳包")
                .setUid(CommonUtils.randomUUID())
                .build();
        return commonMessage.toBuilder().setHeartBeat(heartBeat).build();
    }
}
