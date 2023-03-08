package com.techreturners.pokerHands.vo;

import java.util.Objects;

public class Card {
    private String value;
    private String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public static Card parse(String cardValue) {
        if(Objects.nonNull(cardValue) && cardValue.length() == 2) {
            return new Card(cardValue.substring(0,1), cardValue.substring(1));
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "value='" + value + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}
