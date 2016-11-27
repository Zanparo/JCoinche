package JCoinche;

import io.proto.jcoinche.JCoincheProtos;
import io.proto.jcoinche.JCoincheProtos.tValue;
import io.proto.jcoinche.JCoincheProtos.tAtout;

import static io.proto.jcoinche.JCoincheProtos.tValue.*;

/**
 * Created by samue on 24/11/2016.
 */

public class Card implements ICard{

    private tValue _value;
    private tAtout _color;
    private int _points = 0;

    public Card(tAtout color, tValue value){
        _color = color;
        _value = value;
    }

    public void setPoints(tAtout atout) {
        switch (_value) {
            case NINE:
                if (isAtout(atout)) {
                    _points = 14;
                }
                break;
            case TEN:
                _points = 10;
                break;
            case JACK:
                if (isAtout(atout))
                {
                    _points = 20;
                }
                else
                {
                    _points = 2;
                }
                break;
            case QUEEN:
                _points = 3;
                break;
            case KING:
                _points = 4;
                break;
            case ACE :
                _points = 11;
                break;
        }
    }

    public int getPoints() {
        return _points;
    }

    private boolean isAtout(tAtout atout)
    {
        if (_color == atout || atout == tAtout.FULL)
            return true;
        return false;
    }

    public tAtout getColor(){
        return _color;
    }

    public tValue getValue(){
        return _value;
    }
}
