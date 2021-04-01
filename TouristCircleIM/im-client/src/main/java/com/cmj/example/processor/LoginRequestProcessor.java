package com.cmj.example.processor;

import com.cmj.example.builder.LoginMsgBuilder;
import com.cmj.example.components.ClientSession;
import com.cmj.example.vo.ProtoMsg;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/9
 */
public class LoginRequestProcessor implements BaseProcessor {

    private final ClientSession clientSession;

    public LoginRequestProcessor(ClientSession clientSession) {
        this.clientSession = clientSession;
    }

    @Override
    public ProtoMsg.HeadType headType() {
        return ProtoMsg.HeadType.LOGIN_REQUEST;
    }

    @Override
    public void action() {
        LoginMsgBuilder loginMsgBuilder = new LoginMsgBuilder(clientSession,clientSession.getUserVo());
        ProtoMsg.Message message = loginMsgBuilder.buildLoginRequest();
        // 发送登录请求
        clientSession.writeAndFlush(message);
    }
}
