package com.techreturners.pokerHands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PokerGameTest {

    private PokerGame game;

    @BeforeEach
    void setUp() {
        game = new PokerGame();
    }

    @Test
    void parseHandsTest() {
        game.parseHands("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        List<String> blackHandCards = Arrays.asList("2H", "3D", "5S", "9C", "KD");
        List<String> whiteHandCards = Arrays.asList("2C", "3H", "4S", "8C", "AH");
        Map<String, List<String>> expectedPokerHands = new HashMap<>();
        expectedPokerHands.put("Black", blackHandCards);
        expectedPokerHands.put("White", whiteHandCards);

        assertEquals(expectedPokerHands, game.getPokerHands());
    }
}