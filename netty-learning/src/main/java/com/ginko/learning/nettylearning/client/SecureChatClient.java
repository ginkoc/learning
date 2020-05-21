package com.ginko.learning.nettylearning.client;

import com.ginko.learning.nettylearning.clienthandler.chathandler.SecureChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author ginko
 * @date 4/28/20
 */
public class SecureChatClient {

    private static final String CLOSE_SIGNAL = "bye";
    private final String remoteHost;
    private final int port;

    public SecureChatClient(String remoteHost, int port) {
        this.remoteHost = remoteHost;
        this.port = port;
    }

    public void start() throws Exception {
        final SslContext sslCtx = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE).build();

        NioEventLoopGroup loop = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(loop)
                    .channel(NioSocketChannel.class)
                    .handler(new SecureChatClientInitializer(sslCtx, remoteHost, port));

            Channel ch = bootstrap.connect(remoteHost, port).sync().channel();

            ChannelFuture lastWriteFuture = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            for (;;) {
                msg = reader.readLine();
                if (msg == null) {
                    break;
                }

                lastWriteFuture = ch.writeAndFlush(msg + "\r\n");
                if (CLOSE_SIGNAL.equalsIgnoreCase(msg)) {
                    ch.closeFuture().sync();
                    break;
                }
            }

            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            loop.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println(
                    "Usage: " + SecureChatClient.class.getSimpleName() +
                            " <host> <port>");
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);

        new SecureChatClient(host, port).start();
    }
}

