package com.techreturners.pokerHands;

import com.techreturners.pokerHands.service.PlayerHandType;
import com.techreturners.pokerHands.service.PlayerHandTypeImpl;
import com.techreturners.pokerHands.vo.Card;
import com.techreturners.pokerHands.vo.Player;
import com.techreturners.pokerHands.vo.PokerHandType;

import java.util.*;

public class PokerGame {

    private final PlayerHandType playerHandType;

    public PokerGame () {
        playerHandType = new PlayerHandTypeImpl();
    }

    public String findWinner(List<Player> players) {
        Map<String, PokerHandType> playerHandMap = new HashMap<>();
        for(Player player : players) {
            playerHandMap.put(player.getName(), findPokerHandType(player.getHand()));
        }
        return comparePlayerHands(playerHandMap);
    }

    private String comparePlayerHands(Map<String, PokerHandType> playerHandMap) {
        String winnerName = null;
        PokerHandType winnerPokerHandType = null;
        for(Map.Entry<String, PokerHandType> pokerHand: playerHandMap.entrySet()) {
            if(Objects.nonNull(winnerPokerHandType)){
                if(winnerPokerHandType.getValue() > pokerHand.getValue().getValue()) {
                    winnerName = pokerHand.getKey();
                    winnerPokerHandType = pokerHand.getValue();
                }
             } else {
                winnerName = pokerHand.getKey();
                winnerPokerHandType = pokerHand.getValue();
            }
        }
        return winnerName;
    }

    private PokerHandType findPokerHandType(List<Card> cards) {
        if(Objects.nonNull(playerHandType.getStraightFlushCards(cards))) {
            return PokerHandType.STRAIGHT_FLUSH;
        } else if (Objects.nonNull(playerHandType.getFourOfKind(cards))) {
            return PokerHandType.FOUR_OF_KIND;
        } else if (Objects.nonNull(playerHandType.getFullHouseCards(cards))) {
            return PokerHandType.FULL_HOUSE;
        } else if (Objects.nonNull(playerHandType.getFlushCardDisplay(cards))) {
            return PokerHandType.FLUSH;
        } else if (Objects.nonNull(playerHandType.getStraightCards(cards))) {
            return PokerHandType.STRAIGHT;
        } else if (Objects.nonNull(playerHandType.getThreeOfKindCard(cards))) {
            return PokerHandType.THREE_OF_KIND;
        } else if (Objects.nonNull(playerHandType.getTwoPairCard(cards))) {
            return PokerHandType.TWO_PAIRS;
        } else if (Objects.nonNull(playerHandType.getPairCard(cards))) {
            return PokerHandType.PAIR;
        } else {
            return PokerHandType.HIGH_CARD;
        }
    }

}
