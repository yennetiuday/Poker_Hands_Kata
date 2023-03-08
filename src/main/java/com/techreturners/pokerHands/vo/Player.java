package com.techreturners.pokerHands.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private static final String PLAYER_HANDS_SEPARATOR = " {2}";
    private static final String HANDS_SEPARATOR = ": ";
    private static final String CARDS_SEPARATOR = " ";

    private final String name;
    private final List<String> hand;

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

    public static List<Player> parse(String hands) {
        List<Player> players = new ArrayList<>();
        String[] playersHands = hands.split(PLAYER_HANDS_SEPARATOR);
        for (String playerHand: playersHands) {
            String[] playerAndCards = playerHand.split(HANDS_SEPARATOR);
            players.add(new Player(playerAndCards[0], Arrays.asList(playerAndCards[1].split(CARDS_SEPARATOR))));
        }
        return players;
    }
}
