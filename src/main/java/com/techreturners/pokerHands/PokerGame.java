package com.techreturners.pokerHands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerGame {

    private static final String PLAYER_HANDS_SEPARATOR = "  ";
    private static final String HANDS_SEPARATOR = ": ";

    private static final String CARDS_SEPARATOR = " ";

    private Map<String, List<String>> pokerHands;

    public Map<String, List<String>> getPokerHands() {
        return pokerHands;
    }

    public void setPokerHands(Map<String, List<String>> pokerHands) {
        this.pokerHands = pokerHands;
    }

    public void parseHands(String hands) {
        pokerHands = new HashMap<>();
        String[] playersHands = hands.split(PLAYER_HANDS_SEPARATOR);
        for (String playerHand: playersHands) {
            String[] playerAndCards = playerHand.split(HANDS_SEPARATOR);
            pokerHands.put(playerAndCards[0], Arrays.asList(playerAndCards[1].split(CARDS_SEPARATOR)));
        }
    }
}
