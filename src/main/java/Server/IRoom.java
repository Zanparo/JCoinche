package Server;

import java.util.List;

/**
 * Created by Matthieu Lambert on 25/11/2016.
 */
public interface                IRoom {
    public int                  getNbPlayer();
    public int                  getNbSpectator();
    public void                 addPlayer(ClientSession client);
    public void                 addSpectator(ClientSession client);
    public boolean              deletePlayer(ClientSession client);
    public boolean              deleteSpectator(ClientSession client);
    public ClientSession        getPlayer(int nb);
    public ClientSession        getSpectator(int nb);
    /*
    public void                 setCroupier(Croupier croupier);
    public Croupier             getCroupier();
     */
}
