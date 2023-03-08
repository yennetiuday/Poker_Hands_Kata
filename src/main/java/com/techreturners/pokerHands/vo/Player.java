package com.techreturners.pokerHands.vo;

import java.util.List;

public class Player {
    private String name;
    private List<String> hand;

    public Player(String name, List<String> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public List<String> getHand() {
        return hand;
    }
}
