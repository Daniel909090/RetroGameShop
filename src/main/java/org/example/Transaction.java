package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private final String customerName;
    private final String gameTitle;
    private final ConsoleType console;
    private final double price;
    private final boolean tradeIn;
    private final LocalDateTime date;

    // Static list to hold all transactions in memory
    private static final List<Transaction> transactions = new ArrayList<>();

    // ---------- Constructor ----------
    public Transaction(String customerName, String gameTitle, ConsoleType console,
                       double price, boolean tradeIn) {
        this.customerName = customerName;
        this.gameTitle = gameTitle;
        this.console = console;
        this.price = price;
        this.tradeIn = tradeIn;
        this.date = LocalDateTime.now();
    }

    /**
     * Sells a game to a customer, records the transaction,
     * reduces quantity from inventory, and handles discounts.
     */
    public static void sellGame(Inventory inventory, String gameName, ConsoleType console, Customer customer) {
        for (Game g : inventory.getGames()) {
            if (g.getTitle().equalsIgnoreCase(gameName) && g.getConsoleType() == console) {

                if (g.getQuantity() <= 0) {
                    System.out.println("❌ Out of stock!");
                    return;
                }

                // Adjust quantity
                g.setQuantity(g.getQuantity() - 1);

                // Calculate price with discount if applicable
                double finalPrice = customer.hasDiscount() ? g.getPrice() * 0.9 : g.getPrice();
                customer.setDiscount(false); // discount only applies once

                // Create transaction record
                Transaction t = new Transaction(customer.getName(), g.getTitle(), console, finalPrice, false);
                transactions.add(t);
                customer.addTransaction(t);

                System.out.printf("💰 Sold %s (%s) to %s for £%.2f%n",
                        g.getTitle(), console, customer.getName(), finalPrice);

                if (g.getQuantity() == 0)
                    System.out.println("⚠️ " + g.getTitle() + " is now out of stock!");
                return;
            }
        }

        System.out.println("❌ Game not found in inventory.");
    }

    /**
     * Handles a trade-in: increases game stock, gives discount,
     * and records transaction with £0 price.
     */
    public static void tradeInGame(Inventory inventory, Customer customer, String gameTitle, ConsoleType console) {
        boolean found = false;

        for (Game g : inventory.getGames()) {
            if (g.getTitle().equalsIgnoreCase(gameTitle) && g.getConsoleType() == console) {
                g.setQuantity(Math.min(g.getQuantity() + 1, 10));
                found = true;
                break;
            }
        }

        if (!found) {
            inventory.addGame(new Game(gameTitle, console, 2024, 1, 0.0));
        }

        // Give 10% discount for next purchase
        customer.setDiscount(true);

        Transaction t = new Transaction(customer.getName(), gameTitle, console, 0.0, true);
        transactions.add(t);
        customer.addTransaction(t);

        System.out.printf("♻️ %s traded in %s (%s). Discount applied for next purchase.%n",
                customer.getName(), gameTitle, console);
    }

    // ---------- Display Methods ----------
    public static void displayAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("📭 No transactions recorded yet.");
            return;
        }

        System.out.println("\n=== 🧾 All Transactions ===");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Transaction t : transactions) {
            System.out.printf("%s bought %s (%s) for £%.2f on %s%s%n",
                    t.customerName,
                    t.gameTitle,
                    t.console,
                    t.price,
                    t.date.format(fmt),
                    t.tradeIn ? " [TRADE-IN]" : "");
        }
    }

    // ---------- Getters ----------
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

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%s (%s) £%.2f on %s%s",
                gameTitle, console, price, date.format(fmt), tradeIn ? " [TRADE-IN]" : "");
    }
}
