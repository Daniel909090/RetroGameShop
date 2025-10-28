package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    private Inventory inventory;
    private Customer customer;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        customer = new Customer("Alice", "123 Main Street");
    }

    //  Test 1 — Basic transaction creation and getters
    @Test
    void testTransactionConstructorAndGetters() {
        Transaction t = new Transaction("Alice", "Doom", ConsoleType.PC, 14.99, false);

        assertEquals("Alice", t.getCustomerName());
        assertEquals("Doom", t.getGameTitle());
        assertEquals(ConsoleType.PC, t.getConsole());
        assertEquals(14.99, t.getPrice(), 0.01);
        assertFalse(t.isTradeIn());
        assertNotNull(t.getDate());
    }

    //  Test 2 — Selling a game reduces its stock and creates a transaction
    @Test
    void testSellGameReducesStockAndRecordsTransaction() {
        Game g = inventory.getGames().getFirst();
        int beforeQty = g.getQuantity();

        Transaction.sellGame(inventory, g.getTitle(), g.getConsoleType(), customer);

        int afterQty = g.getQuantity();
        assertTrue(afterQty < beforeQty, "Selling a game should reduce its stock");
        assertFalse(getTransactions().isEmpty(), "Transaction list should not be empty after sale");
    }

    //  Test 3 — Trade-in increases stock, applies discount, and records transaction
    @Test
    void testTradeInGameIncreasesStockAndAppliesDiscount() {
        Game g = inventory.getGames().getFirst();
        int beforeQty = g.getQuantity();

        Transaction.tradeInGame(inventory, customer, g.getTitle(), g.getConsoleType());

        int afterQty = g.getQuantity();

        assertTrue(afterQty >= beforeQty, "Trade-in should increase or maintain stock");
        assertTrue(customer.hasDiscount(), "Customer should receive discount after trade-in");
        assertFalse(getTransactions().isEmpty(), "Trade-in should create a transaction record");
    }

    //  Test 4 — toString format
    @Test
    void testToStringFormat() {
        Transaction t = new Transaction("Alice", "Doom", ConsoleType.PC, 14.99, false);
        String result = t.toString();

        assertTrue(result.contains("Doom"));
        assertTrue(result.contains("PC"));
        assertTrue(result.contains("£14.99"));
    }

    //  Test 5 — Display all transactions (no crash expected)
    @Test
    void testDisplayAllTransactionsDoesNotThrow() {
        Game g = inventory.getGames().getFirst();
        Transaction.sellGame(inventory, g.getTitle(), g.getConsoleType(), customer);

        assertDoesNotThrow(Transaction::displayAllTransactions,
                "Displaying transactions should not throw exceptions");
    }

    //  Test 6 — Customer discount should be consumed after one sale
    @Test
    void testDiscountIsConsumedAfterSale() {
        Game g = inventory.getGames().getFirst();

        // Apply discount manually
        customer.setDiscount(true);
        Transaction.sellGame(inventory, g.getTitle(), g.getConsoleType(), customer);

        assertFalse(customer.hasDiscount(), "Discount should be removed after one sale");
    }

    // --- Helper to access private static transaction list ---
    @SuppressWarnings("unchecked")
    private List<Transaction> getTransactions() {
        try {
            var field = Transaction.class.getDeclaredField("transactions");
            field.setAccessible(true);
            return (List<Transaction>) field.get(null);
        } catch (Exception e) {
            fail("Failed to access transactions list: " + e.getMessage());
            return List.of();
        }
    }
}
