package Client;

/**
 * Created by Matthieu Lambert on 26/11/2016.
 */
public class ClientMain {
    public static void main(String[] args) {
        //System.out.println("Hello World");
        //Room room = new Room(500);
        try
        {
            Client client = new Client();
            Client.run();
        } catch (Exception e) {
            System.out.println("Server : " + e.toString());
        }

    }
}
