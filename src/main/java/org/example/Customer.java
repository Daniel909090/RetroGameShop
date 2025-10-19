package org.example;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String address;
    private boolean hasDiscount; // ✅ Discount flag
    private List<TransactionRecord> transactions = new ArrayList<>();



    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.transactions = new ArrayList<>();
        this.hasDiscount = false;
    }

    public String getName() {
        return name; }

    public String getAddress() {
        return address; }

    public boolean hasDiscount() {
        return hasDiscount; }

    public void setDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount; }

    public void addTransaction(TransactionRecord record) {
        transactions.add(record);
    }

    public List<TransactionRecord> getTransactions() {
        return transactions;
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("📭 No transactions for " + name);
            return;
        }

        System.out.println("\n=== 🧾 Transactions for " + name + " ===");

        // ✅ Consistent date format (date + hour + minute)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (TransactionRecord r : transactions) {
            String formattedDate = r.date().format(formatter);
            System.out.printf("%s (%s) - £%.2f on %s%s%n",
                    r.gameTitle(),
                    r.console(),
                    r.price(),
                    formattedDate,
                    r.tradeIn() ? " [Trade-In]" : "");
        }
    }


    @Override
    public String toString() {
        return name + " (" + address + ") " + (hasDiscount ? "⭐ Discount available" : "");
    }
}
