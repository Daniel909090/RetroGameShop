package org.example;

import java.util.ArrayList;

public class Game extends Item {
    private final ConsoleType console;
    private int quantity;


    public Game(String title, ConsoleType console, int year, int quantity, double price) {
        super(title, price, year);
        this.console = console;
        this.quantity = quantity;
    }

    public ConsoleType getConsoleType() {
        return console;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d) x%d - £%.2f",
                title, console, year, quantity, price);
    }


    // Preload a 10 games list with prices
    public static ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<>();


        games.add(new Game("Pokemon", ConsoleType.NINTENDO, 1996, 5, 10));
        games.add(new Game("Mario Kart", ConsoleType.NINTENDO, 1996, 5, 10));

        games.add(new Game("Minecraft", ConsoleType.XBOX, 2011, 5, 10));
        games.add(new Game("Fortnite", ConsoleType.XBOX, 2017, 5, 10.00));

        games.add(new Game("Tomb Raider", ConsoleType.PLAYSTATION, 1996, 5, 10));
        games.add(new Game("God of War", ConsoleType.PLAYSTATION, 2005, 5, 10));

        games.add(new Game("Sonic ", ConsoleType.SEGA, 1991, 5, 10));
        games.add(new Game("Golden Axe", ConsoleType.SEGA, 1989, 5, 10));

        games.add(new Game("Quake", ConsoleType.PC, 1996, 5, 10));
        games.add(new Game("Doom", ConsoleType.PC, 1993, 5, 10));
        return games;
    }
}
