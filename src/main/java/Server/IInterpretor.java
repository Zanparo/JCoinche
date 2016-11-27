package Server;

/**
 * Created by Matthieu Lambert on 25/11/2016.
 */
public interface        IInterpretor {
    public boolean      checkMessage(String msg);
    public String       interpret(String msg, ClientSession client);
}
