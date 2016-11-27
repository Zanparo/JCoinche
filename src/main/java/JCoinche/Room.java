package JCoinche;

import JCoinche.Enum.tAtout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samue on 24/11/2016.
 */
public class Room {
    private Deck _deck = new Deck();
    private List<Player> _players = new ArrayList<Player>();
    private Team[] _teams = new Team[2];
    private int _scoreMax;
    private Player _currentPlayer;
    private String _roomName;

    public Room(int scoreMax, String name) {
        _roomName = name;
        System.out.println("Room created");
        /*_players.add(new Player("Roger"));
        _players.add(new Player("Philippe"));
        _players.add(new Player("Trevor"));
        _players.add(new Player("Natalie"));
        _teams[0] = new Team(0, _players.get(0), _players.get(1));
        _teams[1] = new Team(1, _players.get(2), _players.get(3));
        _scoreMax = scoreMax;
        _currentPlayer = _players.get((int)(Math.random() * 4));
        launchGame();*/
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
            return true;
        }
        return false;
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
        return _roomName;
    }
}
