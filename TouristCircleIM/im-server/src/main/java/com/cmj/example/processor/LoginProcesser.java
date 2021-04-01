package com.cmj.example.processor;

import com.cmj.example.constants.CommonConstants;
import com.cmj.example.builder.LoginResponceBuilder;
import com.cmj.example.component.ServerSession;
import com.cmj.example.vo.ProtoMsg;
import com.cmj.example.vo.UserVo;

/**
 * @author mengjie_chen
 * @description 登录业务逻辑处理器
 * @date 2020/10/8
 */
public class LoginProcesser extends AbstractServerProcesser {

    private LoginResponceBuilder loginResponceBuilder = new LoginResponceBuilder();

    @Override
    public ProtoMsg.HeadType headType() {
        return ProtoMsg.HeadType.LOGIN_REQUEST;
    }

    @Override
    public boolean action(ServerSession serverSession, ProtoMsg.Message message) {
        UserVo userVo = UserVo.proto2UserVo(message.getLoginRequest());
        long sequence = message.getSequence();

        boolean checked = this.checkUser(userVo);
        if (!checked) {
            // 用户检验失败
            ProtoMsg.Message response = loginResponceBuilder.loginResponse(CommonConstants.ResultEnum.AUTH_FAILED, sequence, "-1");
            serverSession.writeAndFlush(response);
            return false;
        }

        serverSession.setUserVo(userVo);
        serverSession.bind();
        ProtoMsg.Message response = loginResponceBuilder.loginResponse(CommonConstants.ResultEnum.SUCCESS, sequence, serverSession.getSessionId());
        serverSession.writeAndFlush(response);
        return true;
    }

    private boolean checkUser(UserVo userVo) {
        // 校验用户逻辑
        return true;
    }


}
