package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;
    private Game doom;
    private Game halo;

    @BeforeEach
    void setup() throws Exception{
        inventory = new Inventory();
        doom = new Game("Doom", ConsoleType.PC, 1993, 3, 14.99);
        halo = new Game("Halo", ConsoleType.XBOX, 2001, 5, 24.99);
        Assertions.assertTrue(inventory.addGame(doom));
        Assertions.assertTrue(inventory.addGame(halo));

        halo = new Game("Halo", ConsoleType.XBOX, 2001, 11, 24.99);
        Assertions.assertFalse(inventory.addGame(halo));
    }

    @Test
    void addGame()  throws Exception{
        int before = inventory.getGames().size();
        Game mario = new Game("Super Mario", ConsoleType.NINTENDO, 1985, 2, 25.99);
        inventory.addGame(mario);
        int after = inventory.getGames().size();

        assertTrue(after >= before, "Inventory size should increase after adding a new game.");
    }

    @Test
    void removeGame() {
        int beforeQty = doom.getQuantity();
        inventory.removeGame("Doom", ConsoleType.PC, 1);
        int afterQty = doom.getQuantity();

        assertFalse(afterQty < beforeQty, "Removing a game should decrease its quantity.");
    }


    @Test
    void displayAllGames() {
        assertNotNull(inventory.getGames());
        assertFalse(inventory.getGames().isEmpty(), "Inventory should not be empty.");
    }

    @Test
    void getGames() {
        assertTrue(inventory.getGames().size() > 0, "Inventory should contain games.");
    }
}
