package com.techreturners.pokerHands.vo;

public enum PokerHandType {
    STRAIGHT_FLUSH(9, "straight flush"),
    FOUR_OF_KIND(8, "four of a kind"),
    FULL_HOUSE(7, "full house"),
    FLUSH(6, "flush"),
    STRAIGHT(5, "straight"),
    THREE_OF_KIND(4, "three of a kind"),
    TWO_PAIRS(3, "two pairs"),
    PAIR(2, "pair"),
    HIGH_CARD(1, "high card");

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
