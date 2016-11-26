package JCoinche;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samue on 24/11/2016.
 */
public class Round {

    private List<Player> _players = new ArrayList<Player>();
    private Contract _contract;

    public Round(Contract contract, List<Player> players)
    {
        _contract = contract;
        _players = players;
        initRound();
    }

    private void initRound()
    {
        for (Player player : _players)
        {
            for (Card card : player.getCards())
            {
                card.setPoints(_contract.getAtout());
            }
        }
    }

    public Contract getContract()
    {
        return _contract;
    }
}
