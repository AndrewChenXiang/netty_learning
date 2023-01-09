package org.netty_learning_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.CharsetUtil;
import org.netty_learning_1.msg.GameMsgProtocol;

public class GameClientMsgHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        GameMsgProtocol.UserEntryCmd userEntryCmd=GameMsgProtocol.UserEntryCmd.newBuilder().setUserId(1).setHeroAvatar("Hello").build();
        ctx.writeAndFlush(userEntryCmd);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
         System.out.println("message: "+msg.toString());
    }
}
