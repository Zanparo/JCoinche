package Server;

import Client.Client;
import JCoinche.Player;
import JCoinche.Room;

import java.util.List;

/**
 * Created by Matthieu Lambert on 25/11/2016.
 */
public class                Interpretor implements IInterpretor {
    //
    //Private value
    //
    private Server          _server;

    public                  Interpretor(Server server)
    {
        _server = server;
    }

    public boolean          checkMessage(String msg)
    {
        int size = msg.split(":").length;
        System.out.println("size = " + size);
        if (size == 2) {
            return (true);
        } else {
            return (false);
        }
    }

    public String           interpret(String msg, ClientSession client)
    {
        int type = Integer.parseInt(msg.split(":")[0]);
        String toParse = msg.split(":")[1];
        return(this.chooseParsing(type, toParse, client));
    }

    private String          chooseParsing(int type, String msg, ClientSession client)
    {
        String ret = "";

        System.out.println("choseParsing : " + type);
        switch (type)
        {
            case 0:
                ret = "OK";
                break;
            case 1:
                ret = "KO";
                break;
            case 2:
                ret = listRoom();
                break;
            case 3:
                ret = manageRoom(msg, client);
                break;
            case 4:
                ret = manageStart(msg, client);
                break;
            default:
                ret = "1|No parsing for this kind of actions";
        }
        return (ret);
    }

    private String          listRoom()
    {
        String ret = "";

        List<JCoinche.Room> list = _server.getRoom();
        for (Room room: list) {
            ret += " - " + room.getName() + " " + room.getNumberPlayer() + "/4\n";
        }
        if (ret == "")
            ret = "1|There is currently no rooms aviable. Please create one in order to play. (3:Name-of-the-room name)";
        else
            ret = "1|Room list : \n" + ret;
        return (ret);
    }

    private String          manageRoom(String msg, ClientSession client)
    {
        boolean newSession = false;
        String ret = "";
        List<Room> list = _server.getRoom();
        System.out.println("RoomlistSize : " + list.size());
        for (Room room: list) {
            System.out.println("roomename = \"" + room.getName() + "\" || msg = \"" + msg + "\"");
            if (msg.equals(room.getName()))
            {
                Player play = new Player("Emile", client);
                room.addPlayer(play);
                play.joinRoom(room);
                client.addPlayer(play);
                if (room.getNumberPlayer() == 4)
                {
                    room.sendAll("1|If you're ready to start the game type \"4:Ready\"\n");
                    return ("0| ");
                }
                else {
                    ret = "0|Welcome to the room : " + room.getName() + ".";
                }
            }
        }
        if (ret == "")
        {
            Room room = _server.createRoom(msg);
            Player play = new Player("Natalie", client);
            room.addPlayer(play);
            play.joinRoom(room);
            client.addPlayer(play);
            ret = "0|Welcome to the new room : " + room.getName() + ".";
        }
        System.out.println("Return of the function");
        return (ret);
    }

    private String manageStart(String msg, ClientSession client)
    {
        String ret = "";

        int nb = client.getPlayer().getRoom().isReady();
        if (nb < 4)
            ret = "0|" + nb + "/4 players are Ready. Waiting for the other players...";
        else
            client.getPlayer().getRoom().init();
        System.out.println("RET = " + ret);
        return (ret);
    }

}
