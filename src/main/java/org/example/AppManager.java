package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class AppManager {
    private final Inventory inventory = new Inventory();
    private final ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        new AppManager().run();
    }
    //  Public getter for Inventory, needed for testing and integration
    public Inventory getInventory() {
        return inventory;
    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== 🎮 Game Shop Main Menu ===");
            System.out.println("1. Display inventory");
            System.out.println("2. Search game");
            System.out.println("3. Add/Remove game");
            System.out.println("4. Sell game");
            System.out.println("5. Trade-in");
            System.out.println("6. Manage customers");
            System.out.println("7. Exit");
            System.out.print("Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> inventory.displayInventory();
                case 2 -> handleSearch(sc);
                case 3 -> manageInventory(sc);
                case 4 -> handleSale(sc);
                case 5 -> handleTradeIn(sc);
                case 6 -> manageCustomers();
                case 7 -> System.out.println("👋 Goodbye!");
                default -> System.out.println("❌ Invalid choice.");
            }

        } while (choice != 7);
    }

    // --- Menu Options ---


    private void handleSearch(Scanner sc) {
        System.out.print("Enter title or console: ");
        String keyword = sc.nextLine();
        Game g = inventory.searchGame(keyword);
        System.out.println(g != null ? g : "❌ Not found.");
    }


    private void manageInventory(Scanner sc) {
        System.out.println("1. Add Game | 2. Remove Game");
        int opt = sc.nextInt(); sc.nextLine();
        if (opt == 1) addGame(sc);
        else if (opt == 2) removeGame(sc);
    }


    private void addGame(Scanner sc) {
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Console: "); ConsoleType console = ConsoleType.valueOf(sc.nextLine().toUpperCase());
        System.out.print("Year: "); int year = sc.nextInt(); sc.nextLine();
        System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
        System.out.print("Price: "); double price = sc.nextDouble(); sc.nextLine();

        inventory.addGame(new Game(title, console, year, qty, price));
        System.out.println("✅ Game added.");
    }


    private void removeGame(Scanner sc) {
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Console: "); ConsoleType console = ConsoleType.valueOf(sc.nextLine().toUpperCase());
        System.out.print("Quantity to remove: "); int qty = sc.nextInt();
        boolean removed = inventory.removeGame(title, console, qty);
        System.out.println(removed ? "🗑️ Removed successfully." : "❌ Game not found.");
    }



    private void handleSale(Scanner sc) {
        System.out.print("Customer name: "); String name = sc.nextLine();
        System.out.print("Address: "); String address = sc.nextLine();
        Customer c = getOrCreateCustomer(name, address);

        System.out.print("Game name: "); String game = sc.nextLine();
        System.out.print("Console: "); ConsoleType console = ConsoleType.valueOf(sc.nextLine().toUpperCase());

        Transaction.sellGame(inventory, game, console, c);
    }

    private void handleTradeIn(Scanner sc) {
        System.out.print("Customer name: "); String name = sc.nextLine();
        System.out.print("Address: "); String address = sc.nextLine();
        Customer c = getOrCreateCustomer(name, address);

        System.out.print("Game name: "); String game = sc.nextLine();
        System.out.print("Console: "); ConsoleType console = ConsoleType.valueOf(sc.nextLine().toUpperCase());

        Transaction.tradeInGame(inventory, c, game, console);
    }

    private void manageCustomers() {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=== 👥 Customer Management ===");
            System.out.println("1. View all customers");
            System.out.println("2. View a customer's transaction history");
            System.out.println("3. View all transactions");
            System.out.println("4. Back to main menu");
            System.out.print("Choose an option: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Please enter a valid number: ");
                sc.next();
            }
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> displayAllCustomers();
                case 2 -> viewCustomerHistory();
                case 3 -> Transaction.displayAllTransactions();
                case 4 -> System.out.println("↩️ Returning to main menu...");
                default -> System.out.println("❌ Invalid option. Try again.");
            }

        } while (option != 4); //  loop until user chooses “Back”
    }

    //  1) List all customers
    private void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println(" No customers found.");
            return;
        }

        System.out.println("\n===  All Customers (" + customers.size() + ") ===");
        for (Customer c : customers) {
            System.out.println("- " + c); // uses Customer.toString()
        }
    }

    // 📜 2) View a single customer's transaction history
    private void viewCustomerHistory() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();

        Customer match = null;
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                match = c;
                break;
            }
        }

        if (match == null) {
            System.out.println("❌ No customer found with that name.");
            if (!customers.isEmpty()) {
                System.out.println("💡 Existing customers:");
                for (Customer c : customers) System.out.println("  • " + c.getName());
            }
            return;
        }

        match.displayCustomerTransactions();
    }

    private Customer getOrCreateCustomer(String name, String address) {
        return customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(() -> {
                    Customer newC = new Customer(name, address);
                    customers.add(newC);
                    return newC;
                });
    }
}
