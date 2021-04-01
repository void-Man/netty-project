package com.cmj.example.builder;

import com.cmj.example.components.ClientSession;
import com.cmj.example.vo.ChatMessageVo;
import com.cmj.example.vo.ProtoMsg;

import static com.cmj.example.vo.ProtoMsg.HeadType.MESSAGE_REQUEST;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class ChatMessageBuilder extends BaseBuilder {
    private static final ProtoMsg.HeadType headType = MESSAGE_REQUEST;
    private final ClientSession clientSession;

    public ChatMessageBuilder(ProtoMsg.HeadType headType, ClientSession clientSession) {
        super(MESSAGE_REQUEST, clientSession);
        this.clientSession = clientSession;
    }

    public ProtoMsg.Message buildMessageRequest() {
        ProtoMsg.Message commonMessage = super.buildCommonMessage(System.currentTimeMillis());
        ChatMessageVo chatMessageVo = clientSession.getChatMessageVo();
        ProtoMsg.MessageRequest messageRequest = ProtoMsg.MessageRequest.newBuilder()
                .setMessageId(chatMessageVo.getMessageId())
                .setFromId(chatMessageVo.getFromUserId())
                .setToId(chatMessageVo.getToUserId())
                .setTime(chatMessageVo.getSendTime())
                .setMsgType(chatMessageVo.getMessageType())
                .setContent(chatMessageVo.getContent())
                .build();
        return commonMessage.toBuilder().setMessageRequest(messageRequest).build();
    }
}
