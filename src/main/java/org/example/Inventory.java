package org.example;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static final int MAX_STOCK = 10;
    private final List<Game> games;

    public Inventory() {
        this.games = new ArrayList<>(Game.getPreloadedGames());
    }

    public List<Game> getGames() {
        return games;
    }


    public void addGame(Game game) {
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(game.getTitle()) &&
                    g.getConsoleType() == game.getConsoleType()) {
                g.setQuantity(Math.min(MAX_STOCK, g.getQuantity() + game.getQuantity()));
                return;
            }
        }
        games.add(game);
    }

    /**
     *
     * @param title -asks title before delete
     * @param console - console type
     * @param amount - how many to add/delete
     * @return true
     */
    public boolean removeGame(String title, ConsoleType console, int amount) {
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(title) && g.getConsoleType() == console) {
                if (amount >= g.getQuantity()) {
                    games.remove(g);
                } else {
                    g.setQuantity(g.getQuantity() - amount);
                }
                return true;
            }
        }
        return false;
    }


    public Game searchGame(String keyword) {
        return games.stream()
                .filter(g -> g.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        g.getConsoleType().toString().equalsIgnoreCase(keyword))
                .findFirst()
                .orElse(null);
    }


    public void displayInventory() {
        System.out.println("\n=== 🕹️ Current Inventory ===");
        for (ConsoleType console : ConsoleType.values()) {
            System.out.println("\n" + console.getEmoji() + " " + console + " Games:");
            games.stream()
                    .filter(g -> g.getConsoleType() == console)
                    .forEach(System.out::println);
        }
    }
}
