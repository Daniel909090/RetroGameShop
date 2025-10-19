package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private final int MAX_STOCK_PER_GAME = 10;
    private ArrayList<Game> games;

    // Constructor: loads all games when the Inventory is created
    public Inventory() {
        games = Game.getAllGames(); // Loads pre-defined games from Game class
    }

    public void displayAllGames() {
        if (games.isEmpty()) {
            System.out.println("📦 Inventory empty!");
            return;
        }

        System.out.println("\n=== 🕹️ Current Inventory ===");

        // Loop through each console type in order
        for (ConsoleType console : ConsoleType.values()) {
            System.out.println("\n" + getConsoleEmoji(console) + " " + console + " Games:");
            boolean hasGames = false;

            for (Game g : games) {
                if (g.getConsoleType() == console) {
                    System.out.println(g);
                    hasGames = true;
                }
            }

            if (!hasGames) {
                System.out.println("   (No games available)");
            }
        }
    }

    private String getConsoleEmoji(ConsoleType console) {
        return switch (console) {
            case PLAYSTATION -> "🎮";
            case XBOX -> "🧩";
            case NINTENDO -> "🍄";
            case SEGA -> "🕹️";
            case PC -> "💻";
        };
    }

    public void searchGame(String keyword) {
        boolean found = false;
        System.out.println("\n🔍 Search results for: " + keyword);

        // Normalize the keyword for comparison
        String search = keyword.toLowerCase().trim();

        for (Game g : games) {
            String title = g.getTitle().toLowerCase();
            String console = g.getConsoleType().toString().toLowerCase();

            // ✅ Match if the keyword appears in either title or console
            if (title.contains(search) || console.contains(search)) {
                System.out.println(g);
                found = true;
            }
        }



        if (!found) {
            System.out.println("❌ No matching games found.");
        }
    }

     //FOR JUNIT TESTS
    // ✅ For programmatic or JUnit use
    public void addGame(Game game) {
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(game.getTitle())
                    && g.getConsoleType() == game.getConsoleType()) {

                int newQty = g.getQuantity() + game.getQuantity();
                if (newQty > MAX_STOCK_PER_GAME) {
                    g.setQuantity(MAX_STOCK_PER_GAME);
                    System.out.println("⚠️ Stock limited to 10 units.");
                } else {
                    g.setQuantity(newQty);
                }
                return;
            }
        }
        games.add(game);
        System.out.println("✅ Game added: " + game.getTitle());
    }

    // ✅ For automated tests or backend logic — remove a specific number of copies
    public void removeGame(String title, ConsoleType console, int quantity) {
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(title) && g.getConsoleType() == console) {
                int newQty = g.getQuantity() - quantity;

                if (newQty <= 0) {
                    g.setQuantity(0);
                    System.out.println("⚠️ " + g.getTitle() + " is now out of stock!");
                } else {
                    g.setQuantity(newQty);
                    System.out.println("✅ Removed " + quantity + " copy(ies) of " + g.getTitle());
                }
                return;
            }
        }

        System.out.println("❌ Game not found in inventory.");
    }



    // ➕ Add a game with robust validation (quantity, types, ranges)
    public void addGame() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- ➕ Add New Game ---");

        System.out.print("Enter game title: ");
        String title = sc.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return;
        }

        // Console type (loop until valid)
        ConsoleType console;
        while (true) {
            System.out.print("Enter console type (PLAYSTATION, XBOX, NINTENDO, SEGA, PC): ");
            String consoleStr = sc.nextLine().trim().toUpperCase();
            try {
                console = ConsoleType.valueOf(consoleStr);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid console type. Try again.");
            }
        }

        // Find existing game (same title + console) to compute how many we can add
        Game existing = null;
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(title) && g.getConsoleType() == console) {
                existing = g;
                break;
            }
        }

        int existingQty = (existing == null) ? 0 : existing.getQuantity();
        int allowedToAdd = MAX_STOCK_PER_GAME - existingQty;

        if (allowedToAdd <= 0) {
            System.out.println("⚠️ Stock for this game is already at the maximum (" + MAX_STOCK_PER_GAME + ").");
            return;
        }

        // Year (basic sane range; adjust if you like)
        int year = readIntInRange(sc, "Enter year of release", 1970, 2100);

        // Quantity (must be integer and within range allowed)
        int quantity = readIntInRange(sc,
                "Enter quantity (1.." + allowedToAdd + ")", 1, allowedToAdd);

        // Price (non-negative)
        double price = readDoubleMin(sc, "Enter price (£)", 0.0);

        if (existing != null) {
            existing.setQuantity(existingQty + quantity);
            // Optional: update price if you want to overwrite; comment out to keep old price
            existing.setPrice(price);
            System.out.println("📈 Updated stock for: " + existing.getTitle() +
                    " | New stock: " + existing.getQuantity() +
                    " | Price: £" + String.format("%.2f", existing.getPrice()));
        } else {
            Game newGame = new Game(title, console, year, quantity, price);
            games.add(newGame);
            System.out.println("✅ Added new game: " + newGame);
        }
    }

    /* ----------------- Helpers (keep them private inside Inventory) ----------------- */

    private int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val < min || val > max) {
                    System.out.println("❌ Please enter a number between " + min + " and " + max + ".");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid integer.");
            }
        }
    }

    private double readDoubleMin(Scanner sc, String prompt, double minInclusive) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = sc.nextLine().trim();
            try {
                double val = Double.parseDouble(line);
                if (val < minInclusive) {
                    System.out.println("❌ Value must be at least " + minInclusive + ".");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }


    public void removeGame() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- 🗑️ Remove Game Copies ---");

        // ✅ Show current games for reference
        if (games.isEmpty()) {
            System.out.println("📦 Inventory empty! Nothing to remove.");
            return;
        }

        System.out.println("\nHere are some available games:");
        for (Game g : games) {
            System.out.println("• " + g.getTitle() + " (" + g.getConsoleType() + ") x" + g.getQuantity());
        }

        System.out.print("\nEnter game title to remove: ");
        String title = sc.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return;
        }

        // ✅ Validate console type (loop until valid)
        ConsoleType console;
        while (true) {
            System.out.print("Enter console type (PLAYSTATION, XBOX, NINTENDO, SEGA, PC): ");
            String consoleStr = sc.nextLine().trim().toUpperCase();
            try {
                console = ConsoleType.valueOf(consoleStr);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid console type. Try again.");
            }
        }

        // ✅ Search for exact game
        Game targetGame = null;
        for (Game g : games) {
            if (g.getTitle().equalsIgnoreCase(title) && g.getConsoleType() == console) {
                targetGame = g;
                break;
            }
        }

        // ✅ If not found, show suggestions by partial name or console
        if (targetGame == null) {
            System.out.println("❌ Game not found for that title/console.");
            System.out.println("💡 Similar games you might mean:");
            String search = title.toLowerCase();
            boolean any = false;
            for (Game g : games) {
                if (g.getTitle().toLowerCase().contains(search) ||
                        g.getConsoleType().toString().toLowerCase().contains(search)) {
                    System.out.println("• " + g.getTitle() + " (" + g.getConsoleType() + ") x" + g.getQuantity());
                    any = true;
                }
            }
            if (!any) {
                System.out.println("   (No similar titles found)");
            }
            return;
        }

        // ✅ If game found — continue with removal
        int currentStock = targetGame.getQuantity();
        System.out.println("Current stock: " + currentStock);

        int amountToRemove = readIntInRange(sc,
                "Enter how many copies to remove (1.." + currentStock + ")", 1, currentStock);

        int newQty = currentStock - amountToRemove;
        if (newQty > 0) {
            targetGame.setQuantity(newQty);
            System.out.println("📉 Removed " + amountToRemove + " copies of " +
                    targetGame.getTitle() + ". Remaining stock: " + newQty);
        } else {
            games.remove(targetGame);
            System.out.println("🗑️ Removed all copies of " + targetGame.getTitle() +
                    " (" + console + ") from inventory!");
        }
    }

    // ✅ Single getter (no need for both)
    public ArrayList<Game> getGames() {
        return games;
    }
}
