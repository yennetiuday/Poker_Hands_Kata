package com.techreturners.pokerHands;

import com.techreturners.pokerHands.vo.Card;
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
    public void findWinnerTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH"));
        assertEquals("White", actualWinner);
    }

}