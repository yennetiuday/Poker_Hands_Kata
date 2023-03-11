package com.techreturners.pokerHands.service;

import com.techreturners.pokerHands.vo.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerHandTypeTest {

    private PlayerHandType playerHandType;

    @BeforeEach
    void setUp() {
        playerHandType = new PlayerHandTypeImpl();
    }

    @Test
    void testIdentifyHighCard() {
        Card expectedHighCard = Card.parse("KS");
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("4D"),
                Card.parse("3C"),
                Card.parse("JS"),
                Card.parse("KS"));

        Card actualHighCard = playerHandType.getHighCard(cards);

        assertEquals(expectedHighCard.getValue(), actualHighCard.getValue());
        assertEquals(expectedHighCard.getSuit(), actualHighCard.getSuit());
    }

    @Test
    void testIdentifyPair() {
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("3C"),
                Card.parse("3S"),
                Card.parse("4D"),
                Card.parse("KS"));
        assertEquals("3", playerHandType.getPairCard(cards));
    }

    @Test
    void testIdentifyTwoPairs() {
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("3C"),
                Card.parse("3S"),
                Card.parse("4D"),
                Card.parse("4S"));
        List<String> expectedPairs = Arrays.asList("3", "4");
        assertEquals(expectedPairs, playerHandType.getTwoPairCard(cards));
    }

    @Test
    void testIdentifyThreeOfKind() {
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("3C"),
                Card.parse("3S"),
                Card.parse("4H"),
                Card.parse("3D"));

        assertEquals("3", playerHandType.getThreeOfKindCard(cards));
    }

}