package com.example.helloworld;

/**
 * Created by samue on 24/11/2016.
 */
public class Card {
    public enum tColor {
        HEARTS,
        SPADES,
        DIAMONDS,
        CLUBS
    };

    public enum tValue {
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }
    private tValue _value;
    private tColor _color;

    public Card(tColor color, tValue value){
        _color = color;
        _value = value;
    }

    public tColor getColor(){
        return _color;
    }

    public tValue getValue(){
        return _value;
    }
}
