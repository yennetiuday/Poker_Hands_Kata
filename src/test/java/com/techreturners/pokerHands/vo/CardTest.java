package com.techreturners.pokerHands.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void parseTest() {
        Card actualCard = Card.parse("5C");
        Card expectedCard = new Card(5, "C");
        assertEquals(expectedCard.getValue(), actualCard.getValue());
        assertEquals(expectedCard.getCardValue().getDisplay(), actualCard.getCardValue().getDisplay());
        assertEquals(expectedCard.getSuit(), actualCard.getSuit());
    }

    @Test
    void testIdentifyHighCard() {
        Card expectedHighCard = Card.parse("KS");
        List<Card> cards = Arrays.asList(Card.parse("2H"),
                Card.parse("4D"),
                Card.parse("3C"),
                Card.parse("JS"),
                Card.parse("KS"));

        Card actualHighCard = Collections.max(cards);

        assertEquals(expectedHighCard.getValue(), actualHighCard.getValue());
        assertEquals(expectedHighCard.getSuit(), actualHighCard.getSuit());
    }
}