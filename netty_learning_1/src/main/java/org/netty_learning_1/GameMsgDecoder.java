package org.netty_learning_1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.netty_learning_1.msg.GameMsgProtocol;

public class GameMsgDecoder extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        GameMsgProtocol.MsgCode msgCode=null;
        if(msg instanceof GameMsgProtocol.UserEntryCmd){
            msgCode=GameMsgProtocol.MsgCode.USER_ENTRY_CMD;
        }
        switch (msgCode){
            case USER_ENTRY_CMD:

                break;
        }
        System.out.print(msg);
    }
}
