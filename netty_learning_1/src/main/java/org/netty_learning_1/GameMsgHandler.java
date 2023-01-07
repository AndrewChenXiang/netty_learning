package org.netty_learning_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.CharsetUtil;

public class GameMsgHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
         System.out.println("message: "+msg.toString());
        ByteBuf in = (ByteBuf) msg;
        System.out.println(in.toString(CharsetUtil.UTF_8));
        ByteBuf resp= Unpooled.copiedBuffer("收到信息$".getBytes());
        channelHandlerContext.writeAndFlush(resp);
//        BinaryWebSocketFrame frame=(BinaryWebSocketFrame)o;
//        ByteBuf byteBuf=frame.content();
//        byte[] bytesArray=new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(bytesArray);
//        for(byte b:bytesArray){
//            System.out.print(b);
//        }
    }
}
