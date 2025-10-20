package org.example;

import java.util.ArrayList;


public class Game extends Item implements Sellable {
    private ConsoleType console;
    private int quantity;

    /**
     * Constructs a new Game with the given title, console type, release year, quantity and price.
     *
     * @param title    the title of the game
     * @param console  the console type for the game
     * @param year     the release year of the game
     * @param quantity the initial stock quantity
     * @param price    the price of the game
     */
    public Game(String title, ConsoleType console, int year, int quantity, double price) {
        super(title, price, year);
        this.console = console;
        this.quantity = quantity;
    }

    // ✅ Fix: Add this getter so Inventory can access console type
    /**
     * Gets the console type of the game.
     *
     * @return The console type.
     */
    public ConsoleType getConsoleType() {
        return console;
    }

    /**
     * Returns the current stock quantity for this game.
     *
     * @return the quantity currently in stock
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the stock quantity for this game.
     *
     * @param quantity the new stock quantity (must be >= 0)
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates and returns the price of this game.
     * This implements the {@link Sellable} contract.
     *
     * @return the price of the game
     */
    @Override
    public double calculatePrice() {
        return price;
    }

    /**
     * Attempts to sell one unit of this game. If stock is available the
     * quantity is decremented and a confirmation is printed. If out of stock
     * a message is printed instead.
     */
    @Override
    public void sell() {
        if (quantity > 0) {
            quantity--;
            System.out.println("💰 Sold one copy of " + title + ". Remaining stock: " + quantity);
        } else {
            System.out.println("❌ Out of stock for " + title);
        }
    }

    /**
     * Displays a one-line summary of this game to standard output.
     * The output includes title, console type, release year, quantity and price.
     */
    @Override
    public void displayInfo() {
        System.out.printf("%s (%s, %d) x%d - £%.2f%n",
                title, console, year, quantity, price);
    }

    /**
     * Returns a string representation of this game.
     *
     * @return formatted string containing title, console, year, quantity and price
     */
    @Override
    public String toString() {
        return String.format("%s (%s, %d) x%d - £%.2f",
                title, console, year, quantity, price);
    }



    // ✅ Preload 50 games with prices
    /**
     * Returns a prefilled list of sample games used by the application.
     *
     * @return an ArrayList containing sample Game instances
     */
    public static ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<>();

        games.add(new Game("Super Mario Bros", ConsoleType.NINTENDO, 1985, 5, 25.99));
        games.add(new Game("The Legend of Zelda", ConsoleType.NINTENDO, 1986, 4, 29.99));
        games.add(new Game("Metroid", ConsoleType.NINTENDO, 1986, 3, 22.50));
        games.add(new Game("Donkey Kong Country", ConsoleType.NINTENDO, 1994, 5, 19.99));
        games.add(new Game("Star Fox", ConsoleType.NINTENDO, 1993, 2, 24.99));
        games.add(new Game("Pokemon Red", ConsoleType.NINTENDO, 1996, 4, 34.99));
        games.add(new Game("Pokemon Blue", ConsoleType.NINTENDO, 1996, 3, 34.99));
        games.add(new Game("GoldenEye 007", ConsoleType.NINTENDO, 1997, 3, 27.99));
        games.add(new Game("Super Smash Bros", ConsoleType.NINTENDO, 1999, 2, 32.99));
        games.add(new Game("Mario Kart 64", ConsoleType.NINTENDO, 1996, 5, 28.99));

        games.add(new Game("Halo: Combat Evolved", ConsoleType.XBOX, 2001, 4, 24.99));
        games.add(new Game("Halo 2", ConsoleType.XBOX, 2004, 3, 27.99));
        games.add(new Game("Fable", ConsoleType.XBOX, 2004, 3, 21.50));
        games.add(new Game("Crimson Skies", ConsoleType.XBOX, 2003, 2, 18.99));
        games.add(new Game("Forza Motorsport", ConsoleType.XBOX, 2005, 3, 26.99));
        games.add(new Game("Gears of War", ConsoleType.XBOX, 2006, 4, 29.99));
        games.add(new Game("Gears of War 2", ConsoleType.XBOX, 2008, 3, 31.99));
        games.add(new Game("Kameo: Elements of Power", ConsoleType.XBOX, 2005, 2, 17.99));
        games.add(new Game("Perfect Dark Zero", ConsoleType.XBOX, 2005, 2, 19.99));
        games.add(new Game("Jet Set Radio Future", ConsoleType.XBOX, 2002, 3, 23.99));

        games.add(new Game("Crash Bandicoot", ConsoleType.PLAYSTATION, 1996, 4, 22.99));
        games.add(new Game("Spyro the Dragon", ConsoleType.PLAYSTATION, 1998, 3, 21.99));
        games.add(new Game("Final Fantasy VII", ConsoleType.PLAYSTATION, 1997, 4, 32.99));
        games.add(new Game("Gran Turismo", ConsoleType.PLAYSTATION, 1997, 3, 25.99));
        games.add(new Game("Metal Gear Solid", ConsoleType.PLAYSTATION, 1998, 3, 27.99));
        games.add(new Game("Resident Evil 2", ConsoleType.PLAYSTATION, 1998, 2, 26.50));
        games.add(new Game("Tekken 3", ConsoleType.PLAYSTATION, 1997, 4, 23.99));
        games.add(new Game("Tomb Raider", ConsoleType.PLAYSTATION, 1996, 3, 24.99));
        games.add(new Game("Castlevania: Symphony of the Night", ConsoleType.PLAYSTATION, 1997, 2, 33.50));
        games.add(new Game("Silent Hill", ConsoleType.PLAYSTATION, 1999, 3, 28.99));

        games.add(new Game("Sonic the Hedgehog", ConsoleType.SEGA, 1991, 5, 19.99));
        games.add(new Game("Sonic the Hedgehog 2", ConsoleType.SEGA, 1992, 4, 21.50));
        games.add(new Game("Streets of Rage", ConsoleType.SEGA, 1991, 3, 18.99));
        games.add(new Game("Golden Axe", ConsoleType.SEGA, 1989, 2, 17.50));
        games.add(new Game("Shinobi III", ConsoleType.SEGA, 1993, 3, 20.99));
        games.add(new Game("Altered Beast", ConsoleType.SEGA, 1988, 2, 16.99));
        games.add(new Game("Phantasy Star IV", ConsoleType.SEGA, 1993, 2, 22.50));
        games.add(new Game("Ecco the Dolphin", ConsoleType.SEGA, 1992, 3, 18.99));
        games.add(new Game("Virtua Fighter 2", ConsoleType.SEGA, 1994, 3, 20.99));
        games.add(new Game("Panzer Dragoon", ConsoleType.SEGA, 1995, 2, 24.50));

        games.add(new Game("The Last of Us", ConsoleType.PLAYSTATION, 2013, 5, 39.99));
        games.add(new Game("Uncharted 2", ConsoleType.PLAYSTATION, 2009, 3, 34.99));
        games.add(new Game("God of War", ConsoleType.PLAYSTATION, 2005, 4, 28.99));
        games.add(new Game("Bloodborne", ConsoleType.PLAYSTATION, 2015, 3, 37.99));
        games.add(new Game("Red Dead Redemption", ConsoleType.XBOX, 2010, 4, 35.99));
        games.add(new Game("The Witcher 3", ConsoleType.PLAYSTATION, 2015, 3, 38.99));
        games.add(new Game("Minecraft", ConsoleType.XBOX, 2011, 5, 19.99));
        games.add(new Game("Fortnite", ConsoleType.XBOX, 2017, 3, 0.00));
        games.add(new Game("Super Mario Odyssey", ConsoleType.NINTENDO, 2017, 4, 44.99));
        games.add(new Game("Zelda: Breath of the Wild", ConsoleType.NINTENDO, 2017, 4, 49.99));

        games.add(new Game("Doom", ConsoleType.PC, 1993, 5, 14.99));
        games.add(new Game("Quake", ConsoleType.PC, 1996, 4, 17.99));
        games.add(new Game("Age of Empires II", ConsoleType.PC, 1999, 3, 18.99));
        games.add(new Game("The Sims", ConsoleType.PC, 2000, 3, 15.99));
        games.add(new Game("Command & Conquer: Red Alert", ConsoleType.PC, 1996, 3, 16.50));
        games.add(new Game("Diablo II", ConsoleType.PC, 2000, 4, 22.99));
        games.add(new Game("StarCraft", ConsoleType.PC, 1998, 3, 21.99));
        games.add(new Game("Baldur’s Gate", ConsoleType.PC, 1998, 2, 23.50));
        games.add(new Game("RollerCoaster Tycoon", ConsoleType.PC, 1999, 4, 19.99));
        games.add(new Game("Half-Life", ConsoleType.PC, 1998, 4, 19.99));
        return games;
    }
}
