package com.techreturners.pokerHands.service;


import com.techreturners.pokerHands.vo.Card;
import com.techreturners.pokerHands.vo.CardValue;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerHandTypeImpl implements PlayerHandType{

    public static final Long PAIR = 2L;
    public static final Long THREE_OF_KIND = 3L;
    public static final Long FOUR_OF_KIND = 4L;
    public static final Integer TWO_PAIRS = 2;

    @Override
    public Card getHighCard(List<Card> hand) {
        return Collections.max(hand);
    }

    @Override
    public String getPairCard(List<Card> hand) {
        return findPairs(hand).get(0);
    }

    @Override
    public List<String> getTwoPairCard(List<Card> hand) {
        List<String> allPairs = findPairs(hand);
        if(allPairs.size()==TWO_PAIRS) return allPairs;
        else return null;
    }

    @Override
    public String getThreeOfKindCard(List<Card> hand) {
        Map<String, Long> cardCounts = countCards(hand);
        return cardCounts.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), THREE_OF_KIND))
                .map(Map.Entry::getKey).toList().get(0);
    }

    @Override
    public Map<Long, String> getFullHouseCards(List<Card> hand) {
        Map<Long, String> fullHouse = new LinkedHashMap<>();
        fullHouse.put(THREE_OF_KIND, getThreeOfKindCard(hand));
        fullHouse.put(PAIR, getPairCard(hand));
        return fullHouse;
    }

    @Override
    public String getFourOfKind(List<Card> hand) {
        Map<String, Long> cardCounts = countCards(hand);
        return cardCounts.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), FOUR_OF_KIND))
                .map(Map.Entry::getKey).toList().get(0);
    }

    @Override
    public String getStraightCard(List<Card> hand) {
        return null;
    }

    private Map<String, Long> countCards(List<Card> cards) {
        return cards.stream()
                .map(Card :: getCardValue)
                .map(CardValue::getDisplay)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private List<String> findPairs(List<Card> hand) {
        Map<String, Long> cardCounts = countCards(hand);
        return cardCounts.entrySet().stream()
                .filter(e-> Objects.equals(e.getValue(), PAIR))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
