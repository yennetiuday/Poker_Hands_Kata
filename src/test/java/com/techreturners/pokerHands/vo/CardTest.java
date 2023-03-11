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

}