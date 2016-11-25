package JCoinche;

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
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.ACE));
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.SEVEN));
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.EIGHT));
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.NINE));
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.TEN));
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.JACK));
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.QUEEN));
        _cards.add(new Card(Card.tColor.CLUBS, Card.tValue.KING));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.ACE));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.SEVEN));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.EIGHT));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.NINE));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.TEN));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.JACK));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.QUEEN));
        _cards.add(new Card(Card.tColor.HEARTS, Card.tValue.KING));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.ACE));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.SEVEN));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.EIGHT));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.NINE));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.TEN));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.JACK));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.QUEEN));
        _cards.add(new Card(Card.tColor.DIAMONDS, Card.tValue.KING));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.ACE));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.SEVEN));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.EIGHT));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.NINE));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.TEN));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.JACK));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.QUEEN));
        _cards.add(new Card(Card.tColor.SPADES, Card.tValue.KING));
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
