package com.techreturners.pokerHands;

import com.techreturners.pokerHands.service.PlayerHandType;
import com.techreturners.pokerHands.service.PlayerHandTypeImpl;
import com.techreturners.pokerHands.util.Util;
import com.techreturners.pokerHands.vo.*;

import java.util.*;
import java.util.stream.Collectors;

public class PokerGame {

    private WinnerDetails winnerDetails;

    private final PlayerHandType playerHandType;

    public PokerGame () {
        playerHandType = new PlayerHandTypeImpl();
        this.winnerDetails = new WinnerDetails();
    }

    public String findWinner(List<Player> players) {
        Map<String, PokerHandType> playerHandMap = new HashMap<>();
        for(Player player : players) {
            playerHandMap.put(player.getName(), findPokerHandType(player.getHand()));
        }
        return comparePlayerHands(playerHandMap, players);
    }

    private PokerHandType findPokerHandType(List<Card> cards) {
        if(Objects.nonNull(playerHandType.getStraightFlushCards(cards))) {
            return PokerHandType.STRAIGHT_FLUSH;
        } else if (Objects.nonNull(playerHandType.getFourOfKind(cards))) {
            return PokerHandType.FOUR_OF_KIND;
        } else if (isFullHouse(cards)) {
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

    private boolean isFullHouse(List<Card> cards) {
        Map<Long, String> fullHouse = playerHandType.getFullHouseCards(cards);
        return Objects.nonNull(fullHouse) &&
                Objects.nonNull(fullHouse.get(Util.THREE_OF_KIND)) &&
                Objects.nonNull(fullHouse.get(Util.PAIR));
    }

    private String comparePlayerHands(Map<String, PokerHandType> playerHandMap, List<Player> players) {
        String winnerName = null;
        PokerHandType winnerPokerHandType = null;
        for(Map.Entry<String, PokerHandType> pokerHand: playerHandMap.entrySet()) {
            if(Objects.nonNull(winnerPokerHandType)){
                if(pokerHand.getValue().getValue() > winnerPokerHandType.getValue()) {
                    winnerName = pokerHand.getKey();
                    winnerPokerHandType = pokerHand.getValue();
                } else if (pokerHand.getValue().getValue() == winnerPokerHandType.getValue()){
                    winnerName = comparePlayerHandsWhenEqual(winnerPokerHandType, players);
                    winnerPokerHandType = pokerHand.getValue();
                }
             } else {
                winnerName = pokerHand.getKey();
                winnerPokerHandType = pokerHand.getValue();
            }
        }
        winnerDetails.setName(winnerName);
        winnerDetails.setHandType(winnerPokerHandType);
        if(!winnerName.equals(Util.TIE)) {
            setWinningHand(players, winnerName);
        }
        return prepareWinningMessage();
    }

    public void setWinningHand(List<Player> players, String winner) {
        winnerDetails.setWinnerHand(players.stream()
                .filter(player -> winner.equals(player.getName())).findFirst().get().getHand());
    }

    private String prepareWinningMessage() {
        if(Util.TIE.equals(winnerDetails.getName())) {
            return winnerDetails.getName();
        } else if (isHighCardCheckRequired(winnerDetails.getHandType())) {
            winnerDetails.setCardValue(playerHandType.getHighCard(winnerDetails.getWinnerHand()).getCardValue());
            return finalMessage();
        } else if (PokerHandType.FOUR_OF_KIND.equals(winnerDetails.getHandType())) {
            winnerDetails.setCardValue(new CardValue(playerHandType.getFourOfKind(winnerDetails.getWinnerHand())));
            return finalMessage();
        } else if (PokerHandType.THREE_OF_KIND.equals(winnerDetails.getHandType())) {
            winnerDetails.setCardValue(new CardValue(playerHandType.getThreeOfKindCard(winnerDetails.getWinnerHand())));
            return finalMessage();
        } else if (PokerHandType.PAIR.equals(winnerDetails.getHandType())) {
            winnerDetails.setCardValue(new CardValue(playerHandType.getPairCard(winnerDetails.getWinnerHand())));
            return finalMessage();
        } else if (PokerHandType.TWO_PAIRS.equals(winnerDetails.getHandType())) {
            winnerDetails.setMessage(playerHandType.getTwoPairCard(winnerDetails.getWinnerHand()).stream()
                    .collect(Collectors.joining(" and ")));
            return finalMessage();
        } else if (PokerHandType.FULL_HOUSE.equals(winnerDetails.getHandType())) {
            Map<Long, String> fullHouse = playerHandType.getFullHouseCards(winnerDetails.getWinnerHand());
            StringBuilder sb = new StringBuilder().append(fullHouse.get(Util.THREE_OF_KIND))
                    .append(" over ").append(fullHouse.get(Util.PAIR));
            winnerDetails.setMessage(sb.toString());
            return finalMessage();
        } else {
            return winnerDetails.getName();
        }
    }

    private String finalMessage() {
        if (Objects.nonNull(winnerDetails.getMessage())) {
            return String.format("%s wins. with %s: %s", winnerDetails.getName(),
                    winnerDetails.getHandType().getLabel(),
                    winnerDetails.getMessage());
        } else {
            return String.format("%s wins. with %s: %s", winnerDetails.getName(),
                    winnerDetails.getHandType().getLabel(),
                    winnerDetails.getCardValue().getDisplay());
        }
    }

    private String comparePlayerHandsWhenEqual(PokerHandType pokerHandType, List<Player> players) {
        String winner =null;
        if(isHighCardCheckRequired(pokerHandType)) {
            winner = compareHighCard(players);
        } else if (pokerHandType.equals(PokerHandType.FOUR_OF_KIND) ||
                pokerHandType.equals(PokerHandType.THREE_OF_KIND) ||
                pokerHandType.equals(PokerHandType.PAIR)) {
            winner = findWinnerWhenPlayerHandTypesEqualWitherFourOrThreeORTwoCardsMatch(pokerHandType, players);
        } else if (pokerHandType.equals(pokerHandType.TWO_PAIRS)) {

        }
        return winner;
    }

    private void findWinnerWhenTwoPairsForBothPlayers(List<Player> players) {
        String winner = null;

//        playerHandType.getTwoPairCard();
    }

    private String findWinnerWhenPlayerHandTypesEqualWitherFourOrThreeORTwoCardsMatch(PokerHandType pokerHandType, List<Player> players) {
        String winner = null;
        CardValue winningCard = null;
        List<Card> playerHand = new ArrayList<>();
        for (Player player: players) {
            CardValue cardValue=null;
            if (pokerHandType.equals(PokerHandType.FOUR_OF_KIND)) {
                cardValue= new CardValue(playerHandType.getFourOfKind(player.getHand()));
            } else if (pokerHandType.equals(PokerHandType.THREE_OF_KIND)) {
                cardValue= new CardValue(playerHandType.getThreeOfKindCard(player.getHand()));
            } else if (pokerHandType.equals(PokerHandType.PAIR)) {
                cardValue= new CardValue(playerHandType.getPairCard(player.getHand()));
            }
            if(Objects.nonNull(winner) && cardValue.getValue()>winningCard.getValue()) {
                winner = player.getName();
                winningCard = cardValue;
                playerHand.addAll(player.getHand());
            } else if(Objects.nonNull(winner) && cardValue.getValue()==winningCard.getValue()) {
                winner = Util.TIE;
            } else {
                winner = player.getName();
                winningCard = cardValue;
                playerHand.addAll(player.getHand());
            }
        }
        winnerDetails.setName(winner);
        winnerDetails.setWinnerHand(playerHand);
        return winner;
    }

    private boolean isHighCardCheckRequired(PokerHandType pokerHandType) {
        List<PokerHandType> highCardCheckRequiredList = Arrays.asList(PokerHandType.STRAIGHT_FLUSH,
                PokerHandType.FLUSH,
                PokerHandType.STRAIGHT,
                PokerHandType.HIGH_CARD);
        return highCardCheckRequiredList.contains(pokerHandType);
    }

    private String compareHighCard(List<Player> players) {
        String winner=null;
        Card winnerHighCard = null;
        List<Card> playerHand = new ArrayList<>();
        for (Player player: players) {
            Card playerHighCard =  playerHandType.getHighCard(player.getHand());
            if(Objects.nonNull(winner)) {
                if(playerHighCard.getValue() > winnerHighCard.getValue()) {
                    winner = player.getName();
                    winnerHighCard = playerHighCard;
                    playerHand.addAll(player.getHand());
                } else if (playerHighCard.getValue() == winnerHighCard.getValue()){
                    winner = Util.TIE;
                }
            } else {
                winner = player.getName();
                winnerHighCard = playerHighCard;
                playerHand.addAll(player.getHand());
            }
        }
        winnerDetails.setName(winner);
        winnerDetails.setCardValue(winnerHighCard.getCardValue());
        winnerDetails.setWinnerHand(playerHand);
        return winner;
    }

}
