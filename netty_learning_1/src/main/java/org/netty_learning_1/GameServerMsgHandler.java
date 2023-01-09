package org.netty_learning_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.netty_learning_1.msg.GameMsgProtocol;

public class GameServerMsgHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("message: "+msg.toString());
        if(msg instanceof GameMsgProtocol.UserEntryCmd){
            GameMsgProtocol.UserEntryCmd u=(GameMsgProtocol.UserEntryCmd)msg;
            System.out.println("id:"+u.getUserId());
            ByteBuf resp= Unpooled.copiedBuffer("收到信息$".getBytes());
            channelHandlerContext.writeAndFlush(resp);
        }
    }
}
