package com.cmj.example.component;

import com.cmj.example.constants.CommonConstants;
import com.cmj.example.vo.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class ProtoBufMessageEncoder extends MessageToByteEncoder<ProtoMsg.Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtoMsg.Message msg, ByteBuf out) throws Exception {
        // 写入魔术
        out.writeShort(CommonConstants.MAGIC_CODE);
        // 写入版本号
        out.writeShort(CommonConstants.VERSION_CODE);

        byte[] array = msg.toByteArray();

        // 写入数据长度
        out.writeInt(array.length);

        // 写入数据内容
        out.writeBytes(array);
    }
}
