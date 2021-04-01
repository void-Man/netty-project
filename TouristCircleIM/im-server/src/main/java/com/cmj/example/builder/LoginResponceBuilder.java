package com.cmj.example.builder;

import com.cmj.example.constants.CommonConstants;
import com.cmj.example.vo.ProtoMsg;

/**
 * @author mengjie_chen
 * @description 登录响应vo builder
 * @date 2020/10/7
 */
public class LoginResponceBuilder {

    public ProtoMsg.Message loginResponse(CommonConstants.ResultEnum resultEnum, long seqId, String sessionId) {
        // 创建message对象builder
        ProtoMsg.Message.Builder messageBuilder = ProtoMsg.Message.newBuilder()
                .setType(ProtoMsg.HeadType.LOGIN_RESPONSE)
                .setSequence(seqId)
                .setSessionId(sessionId);

        // 创建LoginResponse对象builder
        ProtoMsg.LoginResponse.Builder loginResponseBuilder = ProtoMsg.LoginResponse.newBuilder()
                .setIsSuccess(resultEnum.isSuccess())
                .setCode(resultEnum.getCode())
                .setDesc(resultEnum.getDesc())
                .setExpose(1);
        messageBuilder.setLoginResponse(loginResponseBuilder.build());
        return messageBuilder.build();
    }
}
