package com.techreturners.pokerHands.service;

import com.techreturners.pokerHands.vo.Card;

import java.util.List;

public interface PlayerHandType {
    Card getHighCard(List<Card> hand);

    String getPairCard(List<Card> hand);

    List<String> getTwoPairCard(List<Card> hand);

    String getThreeOfKindCard(List<Card> hand);

    String getFullHouseCard(List<Card> hand);
}
