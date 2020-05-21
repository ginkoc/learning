package com.ginko.learning.snr_integration.nettyserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author ginko
 * @date 5/6/20
 */
public class AirDataDecoder extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ADSBFrame adsbFrame = (ADSBFrame) msg;
        if (adsbFrame.validateUniqueSymbol() &&
                adsbFrame.validateSwapFlag() &&
                adsbFrame.validateFrameType() &&
                adsbFrame.validatePriority() &&
                adsbFrame.validateCRC() &&
                adsbFrame.validateEncryptSymbol()) {
            ctx.fireChannelRead(adsbFrame.getBody());
        } else {
            adsbFrame.getBody().release();
        }
    }
}
