package JCoinche;

/**
 * Created by samue on 24/11/2016.
 */
public class Team  implements ITeam{

    private Player[] _members = new Player[2];
    private int _id;
    private int _score;

    public Team(int id, Player pone, Player ptwo)
    {
        _members[0] = pone;
        _members[1] = ptwo;
        _id = id;
    }

    public int getScore()
    {
        return _score;
    }

    public Player[] getMembers() {
        return _members;
    }

    public void addToScore(int score)
    {
        _score += score;
    }

    public int getId() { return _id; }
}
