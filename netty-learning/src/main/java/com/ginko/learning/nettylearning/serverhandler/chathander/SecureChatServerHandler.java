package com.ginko.learning.nettylearning.serverhandler.chathander;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetAddress;

/**
 * @author ginko
 * @date 4/28/20
 */
public class SecureChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final String CLOSE_SIGNAL = "bye";

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        ctx.pipeline().get(SslHandler.class)
                .handshakeFuture()
                .addListener(future -> {
                    ctx.writeAndFlush("Welcome to " +
                            InetAddress.getLocalHost().getHostName() + " secure chat service!\n");
                    ctx.writeAndFlush("Your session is protected by " +
                            ctx.pipeline().get(SslHandler.class).engine().getSession().getCipherSuite() +
                            " cipher suite.\n");
                    CHANNELS.add(ctx.channel());
                });
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        for (Channel c : CHANNELS) {
            if (c == ctx.channel()) {
                c.writeAndFlush("[you] " + msg + '\n');
            } else {
                c.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + '\n');
            }
        }

        if (CLOSE_SIGNAL.equals(msg.toLowerCase())) {
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
