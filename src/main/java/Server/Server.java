package Server;

// import netty
import JCoinche.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import JCoinche.Room;

// import java
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu Lambert on 25/11/2016.
 */
public class                            Server
{

    //
    //Private value
    //
    private int                         _port;
    private Interpretor                 _interpretor;
    private List<Room>         _rooms;
    private List<ClientSession>         _clients;
    private Server                      _this;

    //
    //public function
    //
    public                              Server(int port)
    {
        this._port = port;
        _rooms = new ArrayList<Room>();
        _clients = new ArrayList<ClientSession>();
        _interpretor = new Interpretor(this);
    }

    public void                         run() throws Exception
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(createSession());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(_port).sync();

            f.channel().closeFuture().sync();
        }
        finally
        {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    //
    //Private function
    //
    private ClientSession               createSession()
    {
        ClientSession client = new ClientSession(_interpretor);
        _clients.add(client);
        return (client);
    }

    public List<Room>                   getRoom()
    {
        return (_rooms);
    }

    public Room createRoom(String name)
    {
        Room newroom = new Room(1500, name);
        _rooms.add(newroom);
        return (newroom);
    }

}
