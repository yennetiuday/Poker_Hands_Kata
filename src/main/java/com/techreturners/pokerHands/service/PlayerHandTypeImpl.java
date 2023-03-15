package com.techreturners.pokerHands.service;

import com.techreturners.pokerHands.util.Util;
import com.techreturners.pokerHands.vo.Card;
import com.techreturners.pokerHands.vo.CardSuit;
import com.techreturners.pokerHands.vo.CardValue;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerHandTypeImpl implements PlayerHandType{

    @Override
    public Card getHighCard(List<Card> hand) {
        return Collections.max(hand);
    }

    @Override
    public String getPairCard(List<Card> hand) {
        List<String> pairs = findPairs(hand);
        return !pairs.isEmpty() ? pairs.get(0) : null;
    }

    @Override
    public List<String> getTwoPairCard(List<Card> hand) {
        List<String> allPairs = findPairs(hand);
        if(allPairs.size()== Util.TWO_PAIRS) return allPairs;
        else return null;
    }

    @Override
    public String getThreeOfKindCard(List<Card> hand) {
        Map<String, Long> cardCounts = countCards(hand);
        List<String> threeOfKind = cardCounts.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), Util.THREE_OF_KIND))
                .map(Map.Entry::getKey).toList();
        return !threeOfKind.isEmpty() ? threeOfKind.get(0) : null;
    }

    @Override
    public Map<Long, String> getFullHouseCards(List<Card> hand) {
        Map<Long, String> fullHouse = new LinkedHashMap<>();
        fullHouse.put(Util.THREE_OF_KIND, getThreeOfKindCard(hand));
        fullHouse.put(Util.PAIR, getPairCard(hand));
        return fullHouse;
    }

    @Override
    public String getFourOfKind(List<Card> hand) {
        Map<String, Long> cardCounts = countCards(hand);
        List<String> fourOfKind = cardCounts.entrySet().stream()
                .filter(e -> e.getValue()==Util.FOUR_OF_KIND)
                .map(Map.Entry::getKey).toList();
//        cardCounts.entrySet().stream().filter(entry -> entry.getValue()==4).findFirst().get().getKey()
        return !fourOfKind.isEmpty() ? fourOfKind.get(0) : null;
    }

    @Override
    public List<String> getStraightCards(List<Card> hand) {
        Map<String, Long> cardCounts = countCards(hand);
        Set<String> handValues = cardCounts.keySet();
        List<List<String>> allPossibleStraights = allPossibleStraights();
        for (List<String> straight : allPossibleStraights) {
            if (new HashSet<>(straight).containsAll(handValues))
                return straight;
        }
        return null;
    }

    @Override
    public CardSuit getFlushCardDisplay(List<Card> hand) {
        Map<CardSuit, Long> suitCountsMap = countSuits(hand);
        Optional<CardSuit> suit = suitCountsMap.keySet().stream().findFirst();
        if(suitCountsMap.size()==Util.ONE && suit.isPresent()) {
            return suit.get();
        }
        return null;
    }

    @Override
    public Map<CardSuit, List<String>> getStraightFlushCards(List<Card> hand) {
        Map<CardSuit, List<String>> straightFlushMap = new HashMap<>();
        CardSuit cardSuit = getFlushCardDisplay(hand);
        List<String> straight = getStraightCards(hand);
        if(!Objects.isNull(cardSuit) && Objects.nonNull(straight) && !straight.isEmpty()){
            straightFlushMap.put(cardSuit, straight);
            return straightFlushMap;
        }
        return null;
    }

    private Map<String, Long> countCards(List<Card> cards) {
        return cards.stream()
                .map(Card :: getCardValue)
                .map(CardValue :: getDisplay)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private List<String> findPairs(List<Card> hand) {
        Map<String, Long> cardCounts = countCards(hand);
        return cardCounts.entrySet().stream()
                .filter(e-> Objects.equals(e.getValue(), Util.PAIR))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private List<List<String>> allPossibleStraights() {
        List<List<String>> allPossibleStraights = new ArrayList<>();
        allPossibleStraights.add(Arrays.asList("A", "2", "3", "4", "5"));
        allPossibleStraights.add(Arrays.asList("2", "3", "4", "5", "6"));
        allPossibleStraights.add(Arrays.asList("3", "4", "5", "6", "7"));
        allPossibleStraights.add(Arrays.asList("4", "5", "6", "7", "8"));
        allPossibleStraights.add(Arrays.asList("5", "6", "7", "8", "9"));
        allPossibleStraights.add(Arrays.asList("6", "7", "8", "9", "T"));
        allPossibleStraights.add(Arrays.asList("7", "8", "9", "T", "J"));
        allPossibleStraights.add(Arrays.asList("8", "9", "T", "J", "Q"));
        allPossibleStraights.add(Arrays.asList("9", "T", "J", "Q", "K"));
        allPossibleStraights.add(Arrays.asList("T", "J", "Q", "K", "A"));
        return allPossibleStraights;
    }

    private Map<CardSuit, Long> countSuits(List<Card> cards) {
        return cards.stream()
                .map(Card :: getSuit)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
