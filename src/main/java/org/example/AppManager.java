package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class AppManager {
    private Inventory inventory;
    private ArrayList<Customer> customers = new ArrayList<>();

    public AppManager() {
        this.inventory = getInventory();
    }

    public static void main(String[] args) {
        AppManager app = new AppManager();
        app.run();

    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== 🎮 Game Shop Main Menu ===");
            System.out.println("1. Display all games");
            System.out.println("2. Search Game");
            System.out.println("3. Manage inventory");
            System.out.println("4. Make sale");
            System.out.println("5. Trade-in game");
            System.out.println("6. Manage customers");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            while (!sc.hasNextInt()) {
                System.out.print("❌ Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> inventory.displayAllGames();
                case 2 -> inventory.searchGame();
                case 3 -> manageInventory();
                case 4 -> makeSale();
                case 5 -> tradeInGame();
                case 6 -> manageCustomers();
                case 7 -> System.out.println(" Exiting program...");
                default -> System.out.println("❌ Invalid option.");
            }

        } while (choice != 7); // ✅ only exits when user chooses Exit
    }




    private void manageInventory() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== ⚙️ Inventory Management ===");
        System.out.println("1. ➕ Add a new game");
        System.out.println("2. 🗑️ Remove a game");
        System.out.println("3. Return to main menu");
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (option) {
            case 1 -> inventory.addGame();
            case 2 -> inventory.removeGame();
            case 3 -> {}
            default -> System.out.println("❌ Invalid option. Please choose 1 or 2.");
        }
    }


    private void makeSale() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter address: ");
        String address = sc.nextLine().trim();

        //  Get existing or create new customer
        Customer customer = getOrCreateCustomer(name, address);

        System.out.print("Enter game name to sell: ");
        String gameName = sc.nextLine().trim();

        System.out.print("Enter console type (PLAYSTATION, XBOX, NINTENDO, SEGA, PC): ");
        String consoleStr = sc.nextLine().trim().toUpperCase();

        try {
            ConsoleType console = ConsoleType.valueOf(consoleStr);

            // ✅ Perform the sale — method handles success/failure messages internally
            Transaction.sellGame(inventory, gameName, console, customer);


        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid console type. Please enter one of: PLAYSTATION, XBOX, NINTENDO, SEGA, PC.");
        }
    }
    private void tradeInGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== ♻️ Trade-In Game ===");

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter customer address: ");
        String address = sc.nextLine();

        Customer customer = getOrCreateCustomer(name, address);

        System.out.print("Enter game title to trade in: ");
        String title = sc.nextLine();

        System.out.print("Enter console type (PLAYSTATION, XBOX, NINTENDO, SEGA, PC): ");
        String consoleStr = sc.nextLine().trim().toUpperCase();

        try {
            ConsoleType console = ConsoleType.valueOf(consoleStr);
            Transaction.tradeInGame(inventory, customer, title, console);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid console type.");
        }
    }

    private void manageCustomers() {
        Scanner sc = new Scanner(System.in);
        int option = 0;

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

        } while (option != 4); // ✅ loop until user chooses “Back”
    }


    private void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("📭 No customers found.");
            return;
        }

        System.out.println("\n=== 👥 All Customers ===");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    private void viewCustomerHistory() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();

        boolean found = false;
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.displayCustomerTransactions(); // ✅ shows their history
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ No customer found with that name.");
        }
    }


    private Customer getOrCreateCustomer(String name, String address) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c; // existing customer found
            }
        }

        // if not found, create new one
        Customer newCustomer = new Customer(name, address);
        customers.add(newCustomer);
        System.out.println("👤 New customer added: " + name);
        return newCustomer;
    }

    public Inventory getInventory() {
        Inventory inventory = new Inventory();
        return inventory;
    }
}
