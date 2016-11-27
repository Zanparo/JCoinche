package JCoinche;

import Server.Server;
import io.netty.bootstrap.Bootstrap;

import java.security.spec.ECField;

/**
 * Created by samue on 25/11/2016.
 */
public class JCoinche {
    public static void main(String[] args) {
        //Room room = new Room(500, "room1");

        try
        {
            Server serv = new Server(4242);
            serv.run();
        } catch (Exception e) {
            System.out.println("Server : " + e.toString());
        }

    }
}
