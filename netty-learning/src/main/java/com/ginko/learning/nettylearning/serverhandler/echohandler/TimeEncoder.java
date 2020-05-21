package com.ginko.learning.nettylearning.serverhandler.echohandler;

import com.ginko.learning.nettylearning.common.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author ginko
 * @date 4/24/20
 */
public class TimeEncoder extends MessageToByteEncoder<UnixTime> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          UnixTime msg, ByteBuf out) throws Exception {
        out.writeInt((int) msg.value());
    }
}
