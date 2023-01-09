package org.netty_learning_1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.netty_learning_1.msg.GameMsgProtocol;

public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast(new ProtobufVarint32FrameDecoder());
                        p.addLast(new ProtobufDecoder(GameMsgProtocol.UserEntryCmd.getDefaultInstance()));
                        p.addLast(new ProtobufDecoder(GameMsgProtocol.UserAttkCmd.getDefaultInstance()));
                        p.addLast(new ProtobufDecoder(GameMsgProtocol.UserAttkResult.getDefaultInstance()));
                        p.addLast(new ProtobufDecoder(GameMsgProtocol.UserEntryResult.getDefaultInstance()));
                        p.addLast(new ProtobufVarint32LengthFieldPrepender());
                        p.addLast(new ProtobufEncoder());
                        p.addLast(new GameClientMsgHandler());
                    }
                });
        try {
            ChannelFuture channelFuture=bootstrap.connect("127.0.0.1",12346).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
