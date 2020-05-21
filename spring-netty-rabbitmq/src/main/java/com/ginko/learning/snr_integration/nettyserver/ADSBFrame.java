package com.ginko.learning.snr_integration.nettyserver;

import io.netty.buffer.ByteBuf;

/**
 * @author ginko
 * @date 5/6/20
 */
public class ADSBFrame {

    private final int uniqueSymbol;
    private final int swapFlag;
    private final byte frameType;
    private final byte priority;
    private final byte[] encryptSymbol;
    private final ByteBuf body;
    private final short crc;

    public ADSBFrame(int uniqueSymbol,
                     int swapFlag,
                     byte frameType,
                     byte priority,
                     byte[] encryptSymbol,
                     ByteBuf body,
                     short crc) {
        this.uniqueSymbol = uniqueSymbol;
        this.swapFlag = swapFlag;
        this.frameType = frameType;
        this.priority = priority;
        this.encryptSymbol = encryptSymbol;
        this.body = body;
        this.crc = crc;
    }

    public ByteBuf getBody() {
        return body;
    }

    public byte[] getEncryptSymbol() {
        return encryptSymbol;
    }

    public boolean validateUniqueSymbol() {
        return true;
    }

    public boolean validateSwapFlag() {
        return true;
    }

    public boolean validateFrameType() {
        return true;
    }

    public boolean validatePriority() {
        return true;
    }

    public boolean validateCRC() {
        return true;
    }

    public boolean validateEncryptSymbol() {
        return true;
    }
}
