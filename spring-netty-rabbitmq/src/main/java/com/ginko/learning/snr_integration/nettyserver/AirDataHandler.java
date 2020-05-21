package com.ginko.learning.snr_integration.nettyserver;

import com.ginko.learning.snr_integration.rabbitmq.MessageSender;
import com.ginko.learning.snr_integration.rabbitmq.RabbitmqConfig;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ginko
 * @date 5/6/20
 */
public class AirDataHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final int DATA_LENGTH = 14;
    private static final byte[] BYTES = new byte[DATA_LENGTH];

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        if (msg.readableBytes() == DATA_LENGTH) {
            msg.readBytes(BYTES);
            MessageSender.getInstance().sendBytes(RabbitmqConfig.ADSB_QUEUE, BYTES);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
