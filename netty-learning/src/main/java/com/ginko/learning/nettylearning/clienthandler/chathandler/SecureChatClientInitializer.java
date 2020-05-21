package com.ginko.learning.nettylearning.clienthandler.chathandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @author ginko
 * @date 4/28/20
 */
public class SecureChatClientInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslContext;
    private final String remoteHost;
    private final int port;

    public SecureChatClientInitializer(SslContext sslContext,
                                       String remoteHost,
                                       int port) {
        this.sslContext = sslContext;
        this.remoteHost =remoteHost;
        this.port = port;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(sslContext.newHandler(ch.alloc(), remoteHost, port));
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new SecureChatClientHandler());
    }
}
