package com.techreturners.pokerHands;

import com.techreturners.pokerHands.vo.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        String[] blackHandCards = {"2H", "3D", "5S", "9C", "KD"};
        String[] whiteHandCards = {"2C", "3H", "4S", "8C", "AH"};

        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player("Black", Arrays.asList(blackHandCards)));
        expectedPlayers.add(new Player("White", Arrays.asList(whiteHandCards)));

        assertEquals(expectedPlayers.get(0).getName(), game.getPlayers().get(0).getName());
        assertEquals(expectedPlayers.get(0).getHand().size(), game.getPlayers().get(0).getHand().size());
        assertEquals(expectedPlayers.get(0).getHand(), game.getPlayers().get(0).getHand());
        assertEquals(expectedPlayers.get(1).getName(), game.getPlayers().get(1).getName());
        assertEquals(expectedPlayers.get(1).getHand().size(), game.getPlayers().get(1).getHand().size());
        assertEquals(expectedPlayers.get(1).getHand(), game.getPlayers().get(1).getHand());
    }
}