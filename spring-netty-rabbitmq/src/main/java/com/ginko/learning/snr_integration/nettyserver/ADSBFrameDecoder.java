package com.ginko.learning.snr_integration.nettyserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author ginko
 * @date 5/6/20
 */
public class ADSBFrameDecoder extends ByteToMessageDecoder {

    private static final int ENCRYPT_SYMBOL_SIZE = 16;
    private static final int BODY_SIZE = 140;
    private int uniqueSymbol;
    private int swapFlag;
    private byte frameType;
    private byte priority;
    private byte[] encryptSymbol = new byte[ENCRYPT_SYMBOL_SIZE];
    private ByteBuf body;
    private short crc;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        uniqueSymbol = in.readInt();
        swapFlag = in.readInt();
        frameType = in.readByte();
        priority = in.readByte();
        in.readBytes(encryptSymbol);
        body = in.readBytes(BODY_SIZE);
        crc = in.readShort();

        out.add(new ADSBFrame(
                uniqueSymbol,
                swapFlag,
                frameType,
                priority,
                encryptSymbol,
                body,
                crc));
    }
}
