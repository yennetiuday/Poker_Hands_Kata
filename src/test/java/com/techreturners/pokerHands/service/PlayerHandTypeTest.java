package com.techreturners.pokerHands.service;

import com.techreturners.pokerHands.vo.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void testIdentifyFourOfKindCard() {
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("3C"),
                Card.parse("3S"),
                Card.parse("3H"),
                Card.parse("3D"));

        assertEquals("3", playerHandType.getFourOfKind(cards));
    }

    @Test
    void testIdentifyFullHouseCards() {
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("3C"),
                Card.parse("3S"),
                Card.parse("3H"),
                Card.parse("2D"));

        Map<Long, String> expectedFullHouseCards = new LinkedHashMap<>();
        expectedFullHouseCards.put(3L, "3");
        expectedFullHouseCards.put(2L, "2");

        assertEquals(expectedFullHouseCards, playerHandType.getFullHouseCards(cards));
    }

    
    void testIdentifyStraightCards() {
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("3C"),
                Card.parse("4S"),
                Card.parse("5H"),
                Card.parse("6D"));
        List<String> expectedStraight = Arrays.asList("2", "3", "4", "5", "6");
        assertEquals(expectedStraight, playerHandType.getStraightCard(cards));
    }

}