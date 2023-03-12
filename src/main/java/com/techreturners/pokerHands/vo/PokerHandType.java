package com.techreturners.pokerHands.vo;

public enum PokerHandType {
    STRAIGHT_FLUSH(9, "Straight Flush"),
    FOUR_OF_KIND(8, "Four of a kind"),
    FULL_HOUSE(7, "Full house"),
    FLUSH(6, "Flush"),
    STRAIGHT(5, "Straight"),
    THREE_OF_KIND(4, "Three of a kind"),
    TWO_PAIRS(3, "Two pairs"),
    PAIR(2, "Pair"),
    HIGH_CARD(1, "High card");

    private final int value;
    private final String label;

    PokerHandType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
