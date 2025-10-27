package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    //  Static list of all transactions (global history)
    private static List<Transaction> records = new ArrayList<>();

    //  Instance fields (representing a single transaction)
    private String customerName;
    private String gameTitle;
    private ConsoleType console;
    private double price;
    private boolean tradeIn;
    private LocalDateTime date;

    //  Constructor
    public Transaction(String customerName, String gameTitle, ConsoleType console, double price, boolean tradeIn) {
        this.customerName = customerName;
        this.gameTitle = gameTitle;
        this.console = console;
        this.price = price;
        this.tradeIn = tradeIn;
        this.date = LocalDateTime.now();

        // Automatically record it globally
        records.add(this);
    }

    //  Getters
    public String getCustomerName() {
        return customerName; }
    public String getGameTitle() {
        return gameTitle; }
    public ConsoleType getConsole() {
        return console; }
    public double getPrice() {
        return price; }
    public boolean isTradeIn() {
        return tradeIn; }
    public LocalDateTime getDate() {
        return date; }

    //  Convert to a readable string
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%s bought %s (%s) for £%.2f on %s%s",
                customerName, gameTitle, console, price, date.format(formatter),
                tradeIn ? " [Trade-In]" : "");
    }


    //  Static Methods (Global transaction management)
    // Record a sale or trade-in
    public static void recordSale(Customer customer, Game game, double price, boolean tradeIn) {
        Transaction record = new Transaction(
                customer.getName(),
                game.getTitle(),
                game.getConsoleType(),
                price,
                tradeIn
        );

        customer.addTransaction(record); // also store it for this customer
        System.out.println("✅ Transaction recorded: " + record);
    }

    // ✅ Display all transactions globally
    public static void displayAllTransactions() {
        System.out.println("\n=== 🧾 Transaction History ===");

        if (records.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction r : records) {
            System.out.println(r);
        }
    }

    // Handle selling a game
    public static void sellGame(Inventory inventory, String gameName, ConsoleType console, Customer customer) {
        for (Game g : inventory.getGames()) {
            if (g.getTitle().equalsIgnoreCase(gameName) && g.getConsoleType() == console) {
                if (g.getQuantity() > 0) {
                    g.setQuantity(g.getQuantity() - 1);

                    double basePrice = g.getPrice();
                    double finalPrice = customer.hasDiscount() ? basePrice * 0.9 : basePrice;

                    recordSale(customer, g, finalPrice, false);
                    customer.setDiscount(false);

                    System.out.println("💰 Sold " + g.getTitle() + " to " + customer.getName() +
                            " for £" + String.format("%.2f", finalPrice));

                    if (g.getQuantity() == 0)
                        System.out.println("⚠️ " + g.getTitle() + " is now out of stock!");
                    return;
                } else {
                    System.out.println("❌ Out of stock!");
                    return;
                }
            }
        }
        System.out.println("❌ Game not found in inventory.");
    }

    // Handle trade-in logic
    public static void tradeInGame(Inventory inventory, Customer customer, String gameTitle, ConsoleType console) {
        Game tradedGame = null;

        // 🔍 Try to find the game in inventory
        for (Game g : inventory.getGames()) {
            if (g.getTitle().equalsIgnoreCase(gameTitle) && g.getConsoleType() == console) {
                int newQty = Math.min(10, g.getQuantity() + 1);
                g.setQuantity(newQty);
                tradedGame = g; // ✅ store the actual game object
                System.out.println("♻️ Trade-in accepted! Increased stock for " + g.getTitle());
                break;
            }
        }



        // Apply discount and record the trade
        customer.setDiscount(true);
        recordSale(customer, tradedGame, 0.0, true);

        System.out.println("💰 " + customer.getName() + " now has a 10% discount on their next purchase!");
    }


    // Return all transactions list
    public static List<Transaction> getAllTransactions() {
        return records;
    }
}
