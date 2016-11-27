package JCoinche;


import io.proto.jcoinche.JCoincheProtos;

/**
 * Created by samue on 26/11/2016.
 */
public interface ICard {

    public void setPoints(JCoincheProtos.tAtout atout);
    public int getPoints();
    public JCoincheProtos.tAtout getColor();
    public JCoincheProtos.tValue getValue();
}
