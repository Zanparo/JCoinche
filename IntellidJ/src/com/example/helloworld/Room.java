package com.example.helloworld;

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

    public Room(int scoreMax) {
        _players.add(new Player("Roger"));
        _players.add(new Player("Philippe"));
        _players.add(new Player("Trevor"));
        _players.add(new Player("Natalie"));
        _teams[0] = new Team(0, _players.get(0), _players.get(1));
        _teams[1] = new Team(1, _players.get(2), _players.get(3));
        _scoreMax = scoreMax;
    }

    public void launchGame()
    {
        init();
    }

    public void init()
    {
        _deck.shuffle();
        _deck.distribute(_players);
        for (Player player : _players)
        {
            System.out.println("For player :" + player.getName());
            player.showHand();
        }
    }

    public void startGame()
    {
        while (!isGameEnded())
        {
            makeContract();

        }
        Team winner = getWinners();
    }

    public void makeContract()
    {


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
}
