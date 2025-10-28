package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    //  Test 1 — Constructor and Getters
    @Test
    void testConstructorAndGetters() {
        Game game = new Game("Doom", ConsoleType.PC, 1993, 5, 14.99);

        assertEquals("Doom", game.getTitle(), "Title should match");
        assertEquals(ConsoleType.PC, game.getConsoleType(), "Console type should match");
        assertEquals(1993, game.getYear(), "Year should match");
        assertEquals(5, game.getQuantity(), "Quantity should match");
        assertEquals(14.99, game.getPrice(), 0.01, "Price should match");
    }

    //  Test 2 — Set Quantity
    @Test
    void testSetQuantity() {
        Game game = new Game("Doom", ConsoleType.PC, 1993, 5, 14.99);
        game.setQuantity(8);
        assertEquals(8, game.getQuantity(), "Quantity should update correctly");
    }

    //  Test 3 — Set Price
    @Test
    void testSetPrice() {
        Game game = new Game("Doom", ConsoleType.PC, 1993, 5, 14.99);
        game.setPrice(19.99);
        assertEquals(19.99, game.getPrice(), 0.01, "Price should update correctly");
    }

    //  Test 4 — toString format
    @Test
    void testToStringFormat() {
        Game game = new Game("Doom", ConsoleType.PC, 1993, 3, 14.99);
        String expected = "Doom (PC, 1993) x3 - £14.99";
        assertEquals(expected, game.toString(), "toString() should match expected format");
    }

    //  Test 5 — Preloaded games list
    @Test
    void testGetAllGames() {
        assertNotNull(Game.getPreloadedGames(), "Preloaded games list should not be null");
        assertFalse(Game.getPreloadedGames().isEmpty(), "Preloaded games list should not be empty");
        assertTrue(Game.getPreloadedGames().size() >= 10, "Should preload at least 10 games");
    }
}
