package com.techreturners.pokerHands.vo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void parseHandsTest() {
        List<Player> actualPlayers = Player.parse("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        List<Player> expectedPlayers = new ArrayList<>();
        List<Card> blackHand = Arrays.asList(Card.parse("2H"),
                Card.parse("3D"),
                Card.parse("5S"),
                Card.parse("9C"),
                Card.parse("KD"));
        List<Card> whiteHand = Arrays.asList(Card.parse("2H"),
                Card.parse("3D"),
                Card.parse("5S"),
                Card.parse("9C"),
                Card.parse("KD"));
        expectedPlayers.add(new Player("Black", blackHand));
        expectedPlayers.add(new Player("White", whiteHand));

        assertEquals(expectedPlayers.get(0).getName(), actualPlayers.get(0).getName());
        assertEquals(expectedPlayers.get(0).getHand().size(), actualPlayers.get(0).getHand().size());
        assertEquals(expectedPlayers.get(1).getName(), actualPlayers.get(1).getName());
        assertEquals(expectedPlayers.get(1).getHand().size(), actualPlayers.get(1).getHand().size());
    }
}