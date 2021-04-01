package com.cmj.example.builder;

import com.cmj.example.constants.CommonConstants;
import com.cmj.example.vo.ProtoMsg;

/**
 * @author mengjie_chen
 * @description 登录响应vo builder
 * @date 2020/10/7
 */
public class ChatResponceBuilder {

    public ProtoMsg.Message chatResponse(CommonConstants.ResultEnum resultEnum, long seqId, String sessionId, String content) {
        // 创建message对象builder
        ProtoMsg.Message.Builder messageBuilder = ProtoMsg.Message.newBuilder()
                .setType(ProtoMsg.HeadType.MESSAGE_RESPONSE)
                .setSequence(seqId)
                .setSessionId(sessionId);

        // 创建LoginResponse对象builder
        ProtoMsg.MessageResponse.Builder messageResponse = ProtoMsg.MessageResponse.newBuilder()
                .setIsSuccess(resultEnum.isSuccess())
                .setCode(resultEnum.getCode())
                .setDesc(content)
                .setExpose(1);
        messageBuilder.setMessageResponse(messageResponse.build());
        return messageBuilder.build();
    }
}
