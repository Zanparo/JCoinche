package JCoinche;

import Server.ClientSession;
import io.proto.jcoinche.JCoincheProtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samue on 24/11/2016.
 */
public class Room {
    private Deck            _deck = new Deck();
    private List<Player>    _players = new ArrayList<Player>();
    private Team[]          _teams = new Team[2];
    private int             _scoreMax;
    private Player          _currentPlayer;
    private String          _roomName;
    private int             _isReady;

    public Room(int scoreMax, String name) {
        _roomName = name;
        _isReady = 0;
        _scoreMax = scoreMax;
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
        //Round round = new Round(makeContract(), _players);
        for (Player player : _players)
        {
            player.showHand();
            for (Card card : player.getCards())
            {
                JCoincheProtos.Card.Builder cardBuilder = JCoincheProtos.Card.newBuilder();
                cardBuilder.setValue(card.getValue());
                cardBuilder.setColor(card.getColor());
                String s = new String(cardBuilder.build().toByteArray());
                player.sendMessage(s);
            }
        }
        //startGame();
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
        Contract contract = new Contract(80, _teams[0], JCoincheProtos.tAtout.HEARTS);
        JCoincheProtos.Contract.Builder build = JCoincheProtos.Contract.newBuilder();
        build.setAtout(contract.getAtout().toString());
        build.setValue(contract.getValue());
        build.setTeam(contract.getTeam().getId());
        System.out.println(build.toString());
        return contract;
    }

    public boolean addPlayer(Player player)
    {
        if (_players.size() < 4) {
            this.sendAll("0|Welcome to \"" + player.getName() + "\" on the room. There is " + (this._players.size() + 1) + " out of 4 to start the game\n");
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

    public int getNumberPlayer()
    {
        return (_players.size());
    }

    public void sendAll(String msg)
    {
        for (Player play: _players)
        {
            play.sendMessage(msg);
        }
        return;
    }

    public int isReady()
    {
        if (_isReady < 4)
            _isReady++;
        return (_isReady);
    }
}
