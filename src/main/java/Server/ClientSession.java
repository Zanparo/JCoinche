package Server;

import io.netty.buffer.ByteBuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by Matthieu Lambert on 25/11/2016.
 */
public class ClientSession extends SimpleChannelInboundHandler<Object> {
    //
    //Private value
    //
    private ByteBuf                 content;
    private ChannelHandlerContext   ctx;
    private Interpretor             _interpretor;
    //private Room                  _room;

    public ClientSession(Interpretor interpretor)
    {
        _interpretor = interpretor;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("New Client Connected.");
        this.ctx = ctx;

        content = ctx.alloc().directBuffer(1024).writeZero(1024);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf in = (ByteBuf) msg;
        if (_interpretor.checkMessage(in.toString(io.netty.util.CharsetUtil.US_ASCII)) == true)
        {
            sendMessage(_interpretor.interpret(in.toString(io.netty.util.CharsetUtil.US_ASCII), this));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    private void generateTraffic(String msg) {
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    public void sendMessage(String msg)
    {
        System.out.println("sendMessage : " + msg);
        if (msg != "")
        {
            ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        }
        return;
    }

}
