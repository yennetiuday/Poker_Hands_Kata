package com.techreturners.pokerHands.vo;

import java.util.List;

public class WinnerDetails {
    private String name;
    private PokerHandType handType;
    private List<Card> winnerHand;
    private CardValue cardValue;

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokerHandType getHandType() {
        return handType;
    }

    public void setHandType(PokerHandType handType) {
        this.handType = handType;
    }

    public List<Card> getWinnerHand() {
        return winnerHand;
    }

    public void setWinnerHand(List<Card> winnerHand) {
        this.winnerHand = winnerHand;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
