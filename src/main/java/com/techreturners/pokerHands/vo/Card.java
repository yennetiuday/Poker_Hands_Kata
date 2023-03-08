package com.techreturners.pokerHands.vo;

import java.util.Objects;

public class Card {
    private final int value;
    private final CardValue cardValue;
    private final CardSuit suit;

    public Card(int value, String suit) {
        this.cardValue = new CardValue(value);
        this.value = this.cardValue.getValue();
        this.suit = CardSuit.of(suit).get();
    }

    public int getValue() {
        return value;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public CardSuit getSuit() {
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
