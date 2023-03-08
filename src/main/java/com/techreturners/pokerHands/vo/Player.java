package com.techreturners.pokerHands.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Player(String name, List<String> hand) {

    private static final String PLAYER_HANDS_SEPARATOR = " {2}";
    private static final String HANDS_SEPARATOR = ": ";
    private static final String CARDS_SEPARATOR = " ";

    public static List<Player> parse(String hands) {
        List<Player> players = new ArrayList<>();
        String[] playersHands = hands.split(PLAYER_HANDS_SEPARATOR);
        for (String playerHand : playersHands) {
            String[] playerAndCards = playerHand.split(HANDS_SEPARATOR);
            players.add(new Player(playerAndCards[0], Arrays.asList(playerAndCards[1].split(CARDS_SEPARATOR))));
        }
        return players;
    }
}
