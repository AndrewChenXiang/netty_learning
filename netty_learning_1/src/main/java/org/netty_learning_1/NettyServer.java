package org.netty_learning_1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.netty_learning_1.msg.GameMsgProtocol;

public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
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
                p.addLast(new GameServerMsgHandler());
            }
        });
        try{
            ChannelFuture f=serverBootstrap.bind(12346).sync();
            if(f.isSuccess()){
                System.out.println("服务器启动成功");
            }
            f.channel().closeFuture().sync();
        } catch (Exception e){

        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
