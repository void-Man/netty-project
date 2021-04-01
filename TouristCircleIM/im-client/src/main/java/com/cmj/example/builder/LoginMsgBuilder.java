package com.cmj.example.builder;

import com.cmj.example.components.ClientSession;
import com.cmj.example.vo.ProtoMsg;
import com.cmj.example.vo.UserVo;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/8
 */
public class LoginMsgBuilder extends BaseBuilder {
    private final UserVo userVo;

    public LoginMsgBuilder(ClientSession clientSession, UserVo userVo) {
        super(ProtoMsg.HeadType.LOGIN_REQUEST, clientSession);
        this.userVo = userVo;
    }

    public ProtoMsg.Message buildLoginRequest() {
        ProtoMsg.Message message = super.buildCommonMessage(System.currentTimeMillis());
        ProtoMsg.LoginRequest.Builder builder = ProtoMsg.LoginRequest.newBuilder()
                .setUserId(userVo.getUserId())
                .setDeviceId(userVo.getDeviceId())
                .setToken(userVo.getToken())
                .setPlatform(userVo.getPlatform());
        return message.toBuilder().setLoginRequest(builder).build();
    }
}
