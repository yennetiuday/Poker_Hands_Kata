package com.techreturners.pokerHands.vo;

import com.techreturners.pokerHands.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public Player(String name, List<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public static List<Player> parse(String hands) {
        List<Player> players = new ArrayList<>();
        String[] playersHands = hands.split(Util.PLAYER_HANDS_SEPARATOR);
        for (String playerHand : playersHands) {
            String[] playerAndCards = playerHand.split(Util.HANDS_SEPARATOR);
            List<String> cards = Arrays.asList(playerAndCards[1].split(Util.CARDS_SEPARATOR));
            List<Card> handCards = new ArrayList<>();
            for(String  card: cards) {
                handCards.add(Card.parse(card));
            }
            players.add(new Player(playerAndCards[0], handCards));
        }
        return players;
    }

}
