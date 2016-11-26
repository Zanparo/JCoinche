package JCoinche;

import JCoinche.Enum.tValue;
import JCoinche.Enum.tAtout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samue on 24/11/2016.
 */
public class Deck {

    private ArrayList<Card> _cards;

    public Deck()
    {
        _cards = new ArrayList<Card>();
        _cards.add(new Card(tAtout.CLUBS, tValue.ACE));
        _cards.add(new Card(tAtout.CLUBS, tValue.SEVEN));
        _cards.add(new Card(tAtout.CLUBS, tValue.EIGHT));
        _cards.add(new Card(tAtout.CLUBS, tValue.NINE));
        _cards.add(new Card(tAtout.CLUBS, tValue.TEN));
        _cards.add(new Card(tAtout.CLUBS, tValue.JACK));
        _cards.add(new Card(tAtout.CLUBS, tValue.QUEEN));
        _cards.add(new Card(tAtout.CLUBS, tValue.KING));
        _cards.add(new Card(tAtout.HEARTS, tValue.ACE));
        _cards.add(new Card(tAtout.HEARTS, tValue.SEVEN));
        _cards.add(new Card(tAtout.HEARTS, tValue.EIGHT));
        _cards.add(new Card(tAtout.HEARTS, tValue.NINE));
        _cards.add(new Card(tAtout.HEARTS, tValue.TEN));
        _cards.add(new Card(tAtout.HEARTS, tValue.JACK));
        _cards.add(new Card(tAtout.HEARTS, tValue.QUEEN));
        _cards.add(new Card(tAtout.HEARTS, tValue.KING));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.ACE));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.SEVEN));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.EIGHT));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.NINE));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.TEN));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.JACK));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.QUEEN));
        _cards.add(new Card(tAtout.DIAMONDS, tValue.KING));
        _cards.add(new Card(tAtout.SPADES, tValue.ACE));
        _cards.add(new Card(tAtout.SPADES, tValue.SEVEN));
        _cards.add(new Card(tAtout.SPADES, tValue.EIGHT));
        _cards.add(new Card(tAtout.SPADES, tValue.NINE));
        _cards.add(new Card(tAtout.SPADES, tValue.TEN));
        _cards.add(new Card(tAtout.SPADES, tValue.JACK));
        _cards.add(new Card(tAtout.SPADES, tValue.QUEEN));
        _cards.add(new Card(tAtout.SPADES, tValue.KING));
    }

    public void shuffle() {
        for (int i = 0; i != 32; i++)
        {
            int random = (int) (Math.random() * _cards.size());
            Card tmp = _cards.get(i);
            _cards.set(i, _cards.get(random));
            _cards.set(random, tmp);
        }
    }

    public ArrayList<Card> getCards() {
        return _cards;
    }

    public boolean distribute(List<Player> players)
    {
        if (players.size() != 4)
        {
            return false;
        }
        for (Player player : players)
        {
            for (int i = 0; i != 8; i++)
            {
                Card tmp = _cards.get(i);
                player.addCard(tmp);
            }
            _cards.removeAll(_cards.subList(0,8));
        }
        return true;
    }

    public void addCards(List<Card> cards)
    {
        _cards.addAll(cards);
    }
}
