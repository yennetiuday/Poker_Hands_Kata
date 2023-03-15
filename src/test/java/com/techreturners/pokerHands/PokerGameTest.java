package com.techreturners.pokerHands;

import com.techreturners.pokerHands.vo.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerGameTest {

    private PokerGame game;

    @BeforeEach
    void setUp() {
        game = new PokerGame();
    }

    @Test
    public void findWinnerHighCardTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH"));
        assertEquals("White wins. with high card: A", actualWinner);
    }

    @Test
    public void findWinnerPairTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 2H 3D 5S 9C 9D  White: 2C 3H 4S 8C AH"));
        assertEquals("Black wins. with pair: 9", actualWinner);
    }

    @Test
    public void findWinnerThreeOfKindTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 9H 3D 5S 9C 9D  White: 2C 3H 4S 8C AH"));
        assertEquals("Black wins. with three of a kind: 9", actualWinner);
    }

    @Test
    public void findWinnerTwoPairsTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H 3D 5S 9C 9D  White: 2C 3H 4S 8C AH"));
        assertEquals("Black wins. with two pairs: 3 and 9", actualWinner);
    }

    @Test
    public void findWinnerStraightTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H 4D 5S 6C 7D  White: 2C 3H 4S 8C AH"));
        assertEquals("Black wins. with straight: 7", actualWinner);
    }

    @Test
    public void findWinnerFlushTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H KH JH AH 7H  White: 2C 3H 4S 8C KH"));
        assertEquals("Black wins. with flush: A", actualWinner);
    }

    @Test
    public void findWinnerFullHouseTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H 3D 3S 9C 9D  White: 2C 3H 4S 8C AH"));
        assertEquals("Black wins. with full house: 3 over 9", actualWinner);
    }

    @Test
    public void findWinnerFourOfKindTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H 3D 3S 3C 9D  White: 2C 3H 4S 8C AH"));
        assertEquals("Black wins. with four of a kind: 3", actualWinner);
    }

    @Test
    public void findWinnerStraightFlushTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H 4H 5H 6H 7H  White: 2C 3H 4S 8C AH"));
        assertEquals("Black wins. with straight flush: 7", actualWinner);
    }

    @Test
    public void findWinnerTwoPlayersHaveStraightFlushWithTieTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H 4H 5H 6H 7H  White: 3C 4C 5C 6C 7C"));
        assertEquals("Tie", actualWinner);
    }

    @Test
    public void findWinnerTwoPlayersHaveStraightFlushTest() {
        String actualWinner = game.findWinner(Player.parse("Black: 3H 4H 5H 6H 7H  White: 2C 3C 4C 5C 6C"));
        assertEquals("Black wins. with straight flush: 7", actualWinner);
    }

}