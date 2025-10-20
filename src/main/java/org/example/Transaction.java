package org.example;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private static List<TransactionRecord> records = new ArrayList<>();

    public static void recordSale(Customer customer, Game game, double price, boolean tradeIn) {
        TransactionRecord record = new TransactionRecord(
                customer.getName(),
                game.getTitle(),
                game.getConsoleType(),
                price,
                tradeIn,
                LocalDateTime.now()
        );

        records.add(record);
        customer.addTransaction(record); // ✅ Add it to the customer's personal history too

        System.out.println("✅ Transaction recorded: " + record);
    }

    public static void displayAllTransactions() {
        System.out.println("\n=== 🧾 Transaction History ===");

        // Format to show only date, hour, and minute
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (TransactionRecord r : records) {
            String formattedDate = r.date().format(formatter); // ✅ clean date

            System.out.printf("%s bought %s (%s) for £%.2f on %s%s%n",
                    r.customerName(),
                    r.gameTitle(),
                    r.console(),
                    r.price(),
                    formattedDate,
                    r.tradeIn() ? " [Trade-In]" : "");
        }
    }


    // Sell a game (reduce quantity and create transaction)
/**     * Sells a game from the inventory to a customer, applying any discounts and updating stock.
     *
     * @param inventory The inventory containing the games.
     * @param gameName  The name of the game to sell.
     * @param console   The console type of the game.
     * @param customer  The customer purchasing the game.
     */
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

    /** Trade in a game (increase quantity and give discount)
     * Handles the trade-in of a game by a customer, updating inventory and applying discounts.
     * @param inventory
     * @param customer
     * @param gameTitle
     * @param console
     */
    public static void tradeInGame(Inventory inventory, Customer customer, String gameTitle, ConsoleType console) {
        for (Game g : inventory.getGames()) {
            if (g.getTitle().equalsIgnoreCase(gameTitle) && g.getConsoleType() == console) {
                // ✅ Increase stock if already exists
                int newQty = g.getQuantity() + 1;
                if (newQty > 10) newQty = 10;
                g.setQuantity(newQty);
                System.out.println("♻️ Trade-in accepted! Increased stock for " + g.getTitle());
                break;
            }
        }

        // ✅ Customer gets 10% discount
        customer.setDiscount(true);

        // ✅ Record trade-in transaction
        recordSale(customer, new Game(gameTitle, console, 0, 1, 0.0), 0.0, true);

        System.out.println("💰 " + customer.getName() + " now has a 10% discount on their next purchase!");
    }
}
