package com.cmj.example.processor;

import com.cmj.example.builder.ChatMessageBuilder;
import com.cmj.example.components.ClientSession;
import com.cmj.example.vo.ProtoMsg;

/**
 * @author mengjie_chen
 * @description date 2020/10/12
 */
public class ChatRequestProcessor implements BaseProcessor {

    private final ClientSession clientSession;

    public ChatRequestProcessor(ClientSession clientSession) {
        this.clientSession = clientSession;
    }

    @Override
    public ProtoMsg.HeadType headType() {
        return ProtoMsg.HeadType.MESSAGE_REQUEST;
    }

    @Override
    public void action() {
        ChatMessageBuilder chatMessageBuilder = new ChatMessageBuilder(headType(), clientSession);
        clientSession.writeAndFlush(chatMessageBuilder.buildMessageRequest());
    }
}
