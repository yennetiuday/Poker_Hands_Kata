package com.techreturners.pokerHands;

import com.techreturners.pokerHands.vo.Player;

import java.util.*;

public class PokerGame {

    private static final String PLAYER_HANDS_SEPARATOR = "  ";
    private static final String HANDS_SEPARATOR = ": ";

    private static final String CARDS_SEPARATOR = " ";

    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void parseHands(String hands) {
        players = new ArrayList<>();
        String[] playersHands = hands.split(PLAYER_HANDS_SEPARATOR);
        for (String playerHand: playersHands) {
            String[] playerAndCards = playerHand.split(HANDS_SEPARATOR);
            players.add(new Player(playerAndCards[0], Arrays.asList(playerAndCards[1].split(CARDS_SEPARATOR))));
        }
    }
}
