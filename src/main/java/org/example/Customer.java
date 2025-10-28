package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final String address;
    private boolean hasDiscount;
    private final List<Transaction> transactions = new ArrayList<>();

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name; }
    public String getAddress() {
        return address; }
    public boolean hasDiscount() {
        return hasDiscount; }
    public void setDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount; }

    public void addTransaction(Transaction t) { transactions.add(t); }

    public void displayCustomerTransactions() {
        System.out.println("\n=== 🧾 Transaction History for " + name + " ===");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this customer.");
            return;
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    @Override
    public String toString() {
        return name + " (" + address + ")" + (hasDiscount ? " 💸 [10% Discount]" : "");
    }
}
