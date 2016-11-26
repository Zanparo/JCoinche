package JCoinche;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samue on 24/11/2016.
 */
public class Player implements IActor {

    private String _name = "";
    private List<Card> _cards = new ArrayList<Card>();
    private int _team = 0;
    private Room _room = null;

    public Player(String name) {
        _name = name;
    }

    public void addCard(Card card) {
        _cards.add(card);
    }

    private Card playCard(int index) {
        Card ret = _cards.get(index);
        _cards.remove(index);
        return ret;
    }

    public List<Card> getCards() {
        return _cards;
    }

    public void setCards(List<Card> _cards) {
        this._cards = _cards;
    }

    public int getTeam() {
        return _team;
    }

    public String getName() {
        return _name;
    }

    public void setTeam(int team) {
        _team = team;
    }

    public void showHand() {
        for (Card card : _cards) {
            System.out.println(card.getValue() + "|" + card.getColor() + "|Points:" + card.getPoints());
        }
    }

    public boolean joinRoom(Room room) {
        if (room.addPlayer(this)) {
            _room = room;
            return true;
        }
        return false;
    }

    public boolean leaveRoom() {
        if (_room != null)
            if (!_room.removePlayer(this)) {
                _room = null;
                return false;
            }
        return true;
    }
}
