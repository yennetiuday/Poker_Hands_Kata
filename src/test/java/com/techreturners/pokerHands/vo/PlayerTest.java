package com.techreturners.pokerHands.vo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void parseHandsTest() {
        List<Player> players = Player.parse("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        String[] blackHandCards = {"2H", "3D", "5S", "9C", "KD"};
        String[] whiteHandCards = {"2C", "3H", "4S", "8C", "AH"};

        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player("Black", Arrays.asList(blackHandCards)));
        expectedPlayers.add(new Player("White", Arrays.asList(whiteHandCards)));

        assertEquals(expectedPlayers.get(0).getName(), players.get(0).getName());
        assertEquals(expectedPlayers.get(0).getHand().size(), players.get(0).getHand().size());
        assertEquals(expectedPlayers.get(0).getHand(), players.get(0).getHand());
        assertEquals(expectedPlayers.get(1).getName(), players.get(1).getName());
        assertEquals(expectedPlayers.get(1).getHand().size(), players.get(1).getHand().size());
        assertEquals(expectedPlayers.get(1).getHand(), players.get(1).getHand());
    }
}