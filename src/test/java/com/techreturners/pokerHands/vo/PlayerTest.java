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

        assertEquals(expectedPlayers.get(0).name(), players.get(0).name());
        assertEquals(expectedPlayers.get(0).hand().size(), players.get(0).hand().size());
        assertEquals(expectedPlayers.get(0).hand(), players.get(0).hand());
        assertEquals(expectedPlayers.get(1).name(), players.get(1).name());
        assertEquals(expectedPlayers.get(1).hand().size(), players.get(1).hand().size());
        assertEquals(expectedPlayers.get(1).hand(), players.get(1).hand());
    }
}