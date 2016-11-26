package JCoinche;

import JCoinche.Enum.tAtout;
import JCoinche.Enum.tValue;

/**
 * Created by samue on 26/11/2016.
 */
public interface ICard {

    public void setPoints(tAtout atout);
    public int getPoints();
    public tAtout getColor();
    public tValue getValue();
}
