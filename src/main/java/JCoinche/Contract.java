package JCoinche;

/**
 * Created by samue on 25/11/2016.
 */
public class Contract {
    private int _value;
    private Team _team;
    private tAtout _atout;
    private boolean _coinche;
    private boolean _surcoinche;

    public Contract(int value, Team team, tAtout atout)
    {
        setValue(value);
        setTeam(team);
        setAtout(atout);
    }

    private boolean isCorrect()
    {
        if (_value >= 80 && _value <= 160 && _value % 10 == 0)
            return true;
        return false;
    }

    public int getValue() {
        return _value;
    }

    public void setValue(int _value) {
        this._value = _value;
    }

    public Team getTeam() {
        return _team;
    }

    public void setTeam(Team _team) {
        this._team = _team;
    }

    public tAtout getAtout() {
        return _atout;
    }

    public void setAtout(tAtout _atout) {
        this._atout = _atout;
    }

    public boolean isCoinche() {
        return _coinche;
    }

    public void setCoinche(boolean _coinche) {
        this._coinche = _coinche;
    }

    public boolean isSurcoinche() {
        return _surcoinche;
    }

    public void setSurcoinche(boolean _surcoinche) {
        this._surcoinche = _surcoinche;
    }
}
