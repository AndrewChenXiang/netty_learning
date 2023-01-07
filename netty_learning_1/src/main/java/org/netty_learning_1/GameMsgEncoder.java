package org.netty_learning_1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty_learning_1.msg.GameMsgProtocol;

public class GameMsgEncoder extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //发送用户信息

        GameMsgProtocol.UserEntryCmd.Builder userEntryCmdOrBuilder= GameMsgProtocol.UserEntryCmd.newBuilder();
        userEntryCmdOrBuilder.setUserId(1);
        userEntryCmdOrBuilder.setHeroAvatar("hero1");
        GameMsgProtocol.UserEntryCmd userEntryCmd= userEntryCmdOrBuilder.build();
        ctx.channel().writeAndFlush(userEntryCmd);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
