package com.techreturners.pokerHands.vo;

import java.util.Optional;
import java.util.stream.Stream;

public enum CardSuit {
    SPADE("S", "Spade"),
    HEART("H", "Heart"),
    DIAMOND("D", "Diamond"),
    CLUB("C", "Club");

    private final String suit;
    private final String label;

    CardSuit(String suit, String label) {
        this.suit = suit;
        this.label = label;
    }

    public static Optional<CardSuit> of(String suit) {
        return Stream.of(CardSuit.values()).filter(cardSuit -> cardSuit.suit.equals(suit)).findFirst();
    }

    @Override
    public String toString() {
        return label;
    }
}
