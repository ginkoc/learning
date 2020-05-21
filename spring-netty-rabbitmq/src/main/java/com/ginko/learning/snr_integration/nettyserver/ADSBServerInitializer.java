package com.ginko.learning.snr_integration.nettyserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * @author ginko
 * @date 5/6/20
 */
public class ADSBServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // get fixed length 168
        pipeline.addLast(new FixedLengthFrameDecoder(168));
        // package 168 bytes to ADSBFrame
        pipeline.addLast(new ADSBFrameDecoder());
        // validate and get 140 bytes as data
        pipeline.addLast(new AirDataDecoder());
        // split 140 byte by delimiter
        pipeline.addLast(new DelimiterBasedFrameDecoder(14, delimiter()));
        pipeline.addLast(new AirDataHandler());
    }

    private ByteBuf[] delimiter() {
        return new ByteBuf[]{
                Unpooled.wrappedBuffer(new byte[]{(byte) 0x1A33, (byte) 0x1A33}),
                Unpooled.wrappedBuffer(new byte[]{(byte) 0x1A33, (byte) 0x1A33}),
        };
    }
}
