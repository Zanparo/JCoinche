package Server;

import JCoinche.*;
import JCoinche.Enum.tAtout;
import com.sun.deploy.util.SessionState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu Lambert on 25/11/2016.
 */
public class                        Room
{

    //
    //Private value
    //
    /*private List<ClientSession>     _spectator;
    private List<ClientSession>     _player;
    private List<Player>            _player;
    private Deck                    _deck;

    //
    //public value
    //
    int                             _nbPlayer;
    int                             _nbSpectator;
    boolean                         _isReady;
    String                          _name;*/

    private Deck _deck = new Deck();
    private List<Player> _players = new ArrayList<Player>();
    private Team[] _teams = new Team[2];
    private int _scoreMax;
    private Player _currentPlayer;
    private String _name;


    public Room(int scoreMax, String name) {
        _name = name;
        _scoreMax = scoreMax;
        //_currentPlayer = _players.get((int)(Math.random() * 4));
        //launchGame();
    }

    public void launchGame()
    {
        init();
    }

    public void init()
    {
        _deck.shuffle();
        _deck.distribute(_players);
        Round round = new Round(makeContract(), _players);
        for (Player player : _players)
        {
            System.out.println("For player :" + player.getName());
            player.showHand();
        }
        //  startGame();
    }

    public void startGame()
    {
        while (!isGameEnded())
        {
            System.out.println("Waiting for " + _currentPlayer.getName() + " to play.");
            Contract contract = makeContract();
            Round round = new Round(contract, _players);
        }
        Team winner = getWinners();
    }

    public Contract makeContract()
    {
        return new Contract(80, _teams[0], tAtout.HEARTS);
    }

    public boolean addPlayer(Player player)
    {
        if (_players.size() < 4) {
            _players.add(player);
            sendAll("Player : " + player.getName() + " have joined the room.\nThere is " + _players.size() + "/4 to start the game.");
            return true;
        }
        return (false);
    }

    public boolean removePlayer(Player player)
    {
        if (_players.contains(player))
        {
            removePlayer(player);
            return true;
        }
        return false;
    }

    public boolean isGameEnded()
    {
        if (_teams[0].getScore() >= _scoreMax || _teams[1].getScore() >= _scoreMax)
            return true;
        return false;
    }

    public Team getWinners()
    {
        if (_teams[0].getScore() >= _scoreMax && _teams[0].getScore() > _teams[1].getScore())
            return _teams[0];
        else
            return _teams[1];
    }

    public String getName()
    {
        return _name;
    }

    public int getNumberPlayer()
    {
        return (_players.size());
    }

    public void sendAll(String msg)
    {
        System.out.println("Sending to all in the room : " + _name);
        for (Player play: _players)
        {
            System.out.println("player send :" + play.getName());
            play.sendMessage(msg);
        }
        return;
    }

}
