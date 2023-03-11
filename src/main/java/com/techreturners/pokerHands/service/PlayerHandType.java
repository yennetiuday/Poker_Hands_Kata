package com.techreturners.pokerHands.service;

import com.techreturners.pokerHands.vo.Card;

import java.util.List;
import java.util.Map;

public interface PlayerHandType {
    Card getHighCard(List<Card> hand);

    String getPairCard(List<Card> hand);

    List<String> getTwoPairCard(List<Card> hand);

    String getThreeOfKindCard(List<Card> hand);

    Map<Long, String> getFullHouseCards(List<Card> hand);

    String getFourOfKind(List<Card> hand);

    String getStraightCard(List<Card> hand);
}
