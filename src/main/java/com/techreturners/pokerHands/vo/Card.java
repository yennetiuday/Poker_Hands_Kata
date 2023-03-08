package com.techreturners.pokerHands.vo;

import java.util.Objects;

public class Card {
    private int value;

    private CardValue cardValue;
    private String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public static Card parse(String cardString) {
        if(Objects.nonNull(cardString) && cardString.length() == 2) {
            CardValue cardValue = new CardValue(cardString.substring(0,1));
            return new Card(cardValue.getValue(), cardString.substring(1));
        }
        return null;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value='" + value + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}
