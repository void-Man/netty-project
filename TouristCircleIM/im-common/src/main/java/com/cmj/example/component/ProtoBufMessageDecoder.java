package com.cmj.example.component;

import com.cmj.example.constants.CommonConstants;
import com.cmj.example.vo.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class ProtoBufMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 长度不足8位(魔数+版本号+内容长度)，放行
        if (in.readableBytes() < 8) {
            return;
        }

        // 标记起始位置
        in.markReaderIndex();
        // 读取魔数
        short magic = in.readShort();
        // 校验魔数
        if (magic != CommonConstants.MAGIC_CODE) {
            new RuntimeException("客户端口令不对:" + ctx.channel().remoteAddress());
        }

        // 读取版本
        short version = in.readShort();
        // 读取数据长度
        int length = in.readInt();
        if (length < 0) {
            new RuntimeException("非法数据");
        }
        // 收到的消息不完整，长度不够
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }

        byte[] arr;
        if (in.hasArray()) {
            // 堆缓冲区
            arr = in.array();
        } else {
            // 直接缓冲区
            arr = new byte[length];
            in.readBytes(arr, 0, length);
        }

        ProtoMsg.Message message = ProtoMsg.Message.parseFrom(arr);
        if (Objects.nonNull(message)) {
            out.add(message);
        }
    }
}
