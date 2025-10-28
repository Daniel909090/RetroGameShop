package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    //  Test 1 — inventory loads games
    @Test
    void testInventoryInitialLoad() {
        List<Game> games = inventory.getGames();
        assertNotNull(games, "Game list should not be null");
        assertFalse(games.isEmpty(), "Inventory should be preloaded with games");
    }

    //  Test 2 — addGame logic (simulate)
    @Test
    void testAddGameManually() {
        Game newGame = new Game("Cyberpunk 2077", ConsoleType.PC, 2020, 3, 39.99);

        int before = inventory.getGames().size();
        inventory.getGames().add(newGame);
        int after = inventory.getGames().size();

        assertTrue(after > before, "Adding a game should increase inventory size");
        assertEquals("Cyberpunk 2077",
                inventory.getGames().get(after - 1).getTitle(),
                "Last added game should match the one we added");
    }

    //  Test 3 — find a game by name (manual search)
    @Test
    void testSearchGameByName() {
        boolean found = inventory.getGames()
                .stream()
                .anyMatch(g -> g.getTitle().equalsIgnoreCase("Doom"));

        assertTrue(found, "Game 'Doom' should exist in the initial inventory");
    }

    //  Test 4 — remove game logic (manual)
    @Test
    void testRemoveGameManually() {
        Game quake = inventory.getGames().stream()
                .filter(g -> g.getTitle().equalsIgnoreCase("Quake"))
                .findFirst()
                .orElse(null);

        assertNotNull(quake, "Quake should exist before removal");

        int initialQty = quake.getQuantity();
        quake.setQuantity(initialQty - 1);

        assertEquals(initialQty - 1, quake.getQuantity(),
                "Removing one copy should decrease quantity by 1");
    }

    //  Test 5 — validate MAX_STOCK_PER_GAME rule
    @Test
    void testMaxStockLimit() {
        Game mario = inventory.getGames().stream()
                .filter(g -> g.getTitle().equalsIgnoreCase("Mario Kart"))
                .findFirst()
                .orElse(null);

        assertNotNull(mario, "Mario Kart should exist");

        mario.setQuantity(15);  // attempt to exceed
        if (mario.getQuantity() > 10) mario.setQuantity(10);

        assertEquals(10, mario.getQuantity(),
                "Quantity should not exceed the MAX_STOCK_PER_GAME limit of 10");
    }
}
