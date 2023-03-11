package com.techreturners.pokerHands.service;

import com.techreturners.pokerHands.vo.Card;

import java.util.List;

public interface PlayerHandType {
    public Card getHighCard(List<Card> hand);

    public String getPairCard(List<Card> hand);

    public List<String> getTwoPairCard(List<Card> hand);

    public String getThreeOfKindCard(List<Card> hand);
}
