package org.example;

//public class TestCopies {
//    //FOR JUNIT TESTS
//    // For programmatic or JUnit use
//    public void addGame(Game game) {
//        for (Game g : games) {
//            if (g.getTitle().equalsIgnoreCase(game.getTitle())
//                    && g.getConsoleType() == game.getConsoleType()) {
//
//                int newQty = g.getQuantity() + game.getQuantity();
//                if (newQty > MAX_STOCK_PER_GAME) {
//                    g.setQuantity(MAX_STOCK_PER_GAME);
//                    System.out.println("⚠️ Stock limited to 10 units.");
//                } else {
//                    g.setQuantity(newQty);
//                }
//                return;
//            }
//        }
//        games.add(game);
//        System.out.println("✅ Game added: " + game.getTitle());
//    }
//
//    // ✅ For automated tests or backend logic — remove a specific number of copies
//    public void removeGame(String title, ConsoleType console, int quantity) {
//        for (Game g : games) {
//            if (g.getTitle().equalsIgnoreCase(title) && g.getConsoleType() == console) {
//                int newQty = g.getQuantity() - quantity;
//
//                if (newQty <= 0) {
//                    g.setQuantity(0);
//                    System.out.println("⚠️ " + g.getTitle() + " is now out of stock!");
//                } else {
//                    g.setQuantity(newQty);
//                    System.out.println("✅ Removed " + quantity + " copy(ies) of " + g.getTitle());
//                }
//                return;
//            }
//        }
//
//        System.out.println("❌ Game not found in inventory.");
//    }
//
//}
