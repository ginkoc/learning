package com.ginko.learning.snr_integration.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ginko
 * @date 5/6/20
 */
@Component
public class ADSBServer implements CommandLineRunner {

    @Value("${netty.server.port}")
    private int port;

    private final EventLoopGroup boss = new NioEventLoopGroup(1);
    private final EventLoopGroup workers = new NioEventLoopGroup();

    public ChannelFuture startService() throws Exception {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(boss, workers)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ADSBServerInitializer());

            return bootstrap.bind(port).sync();
        } catch (Exception e) {
            System.out.println("Start up netty server error!");
            destroyService();
            throw e;
        }
    }

    @Override
    public void run(String... strings) throws Exception {
        ChannelFuture future = startService();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                destroyService();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }

    private void destroyService() {
        System.out.println("Shutdown Netty Server...");
        boss.shutdownGracefully();
        workers.shutdownGracefully();
    }
}
