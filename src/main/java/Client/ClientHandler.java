package Client;

import JCoinche.Card;
import com.google.protobuf.ByteString;
import com.sun.tools.internal.ws.wsdl.document.Input;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.proto.jcoinche.JCoincheProtos;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Matthieu Lambert on 26/11/2016.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {

    private ByteBuf content;
    private ChannelHandlerContext ctx;
    private List<JCoincheProtos.Card> _cards= new ArrayList();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.ctx = ctx;

        // Initialize the message.
        content = ctx.alloc().directBuffer(Client.SIZE).writeZero(Client.SIZE);

        // Send the initial messages.
        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        content.release();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        String[] tab = new String[2];
        tab[0] = (in.toString(io.netty.util.CharsetUtil.US_ASCII)).substring(0, 1);
        tab[1] = (in.toString(io.netty.util.CharsetUtil.US_ASCII)).substring(2);
        System.out.println(tab[1]);

        if (tab[0].equals("1"))
            generateTraffic();
        if (tab[0].equals("2"))
        {
            InputStream stream = new ByteArrayInputStream(tab[1].getBytes());
            //JCoincheProtos.Card card = JCoincheProtos.Card.parseFrom(stream);
            _cards.add(JCoincheProtos.Card.parseFrom(stream));
            //System.out.println(card.getColor());
            //System.out.println(card.getValue());
            System.out.println("-----");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    long counter;

    private void generateTraffic() {
        // Flush the outbound buffer to the socket.
        // Once flushed, generate the same amount of traffic again.
        Scanner scanner = new Scanner(System.in);
        String str = "";
        try {
            str = scanner.next();
        } catch (Exception e) {
            System.out.println("Catch read : " + e);
        }
        ctx.writeAndFlush(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));//.addListener(trafficGenerator);
        //ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
    }

    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
        //@Override
        public void operationComplete(ChannelFuture future) {
            if (future.isSuccess()) {
                generateTraffic();
            } else {
                future.cause().printStackTrace();
                future.channel().close();
            }
        }
    };
}