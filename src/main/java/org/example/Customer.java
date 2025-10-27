package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String address;
    private boolean hasDiscount;
    private List<Transaction> transactions;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.hasDiscount = false;
        this.transactions = new ArrayList<>();
    }

    // --- Getters and Setters ---
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public boolean hasDiscount() {
        return hasDiscount;
    }
    public void setDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    // --- Transaction Handling ---
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void displayCustomerTransactions() {
        System.out.println("\n=== 🧾 Transaction History for " + name + " ===");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this customer.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    @Override
    public String toString() {
        return name + " (" + address + ")" + (hasDiscount ? " 💸 [10% Discount]" : "");
    }
}
