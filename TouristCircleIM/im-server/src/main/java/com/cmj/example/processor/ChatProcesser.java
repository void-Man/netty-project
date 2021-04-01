package com.cmj.example.processor;

import com.cmj.example.builder.ChatResponceBuilder;
import com.cmj.example.component.ServerSession;
import com.cmj.example.component.SessionMap;
import com.cmj.example.constants.CommonConstants;
import com.cmj.example.utils.common.CommonUtils;
import com.cmj.example.vo.ChatMessageVo;
import com.cmj.example.vo.ProtoMsg;

import java.util.List;

/**
 * @author mengjie_chen
 * @description 登录业务逻辑处理器
 * @date 2020/10/8
 */
public class ChatProcesser extends AbstractServerProcesser {

    private ChatResponceBuilder loginResponceBuilder = new ChatResponceBuilder();

    @Override
    public ProtoMsg.HeadType headType() {
        return ProtoMsg.HeadType.MESSAGE_REQUEST;
    }

    @Override
    public boolean action(ServerSession serverSession, ProtoMsg.Message message) {
        ChatMessageVo chatMessageVo = serverSession.getChatMessageVo();
        List<ServerSession> userSessionList = SessionMap.instance().getSessionListByUserId(chatMessageVo.getToUserId());
        if (CommonUtils.isNullOrEmpty(userSessionList)) {
            ProtoMsg.Message chatResponse = loginResponceBuilder.chatResponse(CommonConstants.ResultEnum.USER_UNKNOW, message.getSequence(), serverSession.getSessionId(), CommonConstants.ResultEnum.USER_UNKNOW.getDesc());
            serverSession.writeAndFlush(chatResponse);
            return false;
        }
        for (ServerSession session : userSessionList) {
            ProtoMsg.Message chatResponse = loginResponceBuilder.chatResponse(CommonConstants.ResultEnum.USER_UNKNOW, message.getSequence(), serverSession.getSessionId(), chatMessageVo.getContent());
            session.writeAndFlush(chatResponse);
        }
        return true;
    }

}
