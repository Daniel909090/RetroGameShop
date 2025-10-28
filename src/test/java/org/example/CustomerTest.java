package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("Alice", "123 Main Street");
    }

    //  Test 1 — Constructor values
    @Test
    void testConstructorAndGetters() {
        assertEquals("Alice", customer.getName(), "Customer name should match");
        assertEquals("123 Main Street", customer.getAddress(), "Customer address should match");
        assertFalse(customer.hasDiscount(), "New customer should not have a discount");
    }

    //  Test 2 — Discount flag
    @Test
    void testSetDiscount() {
        customer.setDiscount(true);
        assertTrue(customer.hasDiscount(), "Discount should be applied");
        customer.setDiscount(false);
        assertFalse(customer.hasDiscount(), "Discount should be removed");
    }

    //  Test 3 — Add transaction
    @Test
    void testAddTransaction() {
        Transaction t = new Transaction("Alice", "Doom", ConsoleType.PC, 14.99, false);
        customer.addTransaction(t);

        // Assuming `transactions` list is private, test via reflection of behavior
        // (We know displayCustomerTransactions() loops over transactions)
        assertEquals(1, getTransactionCount(customer),
                "Customer should have 1 transaction after adding");
    }

    //  Test 4 — Multiple transactions
    @Test
    void testMultipleTransactions() {
        Transaction t1 = new Transaction("Alice", "Doom", ConsoleType.PC, 14.99, false);
        Transaction t2 = new Transaction("Alice", "Quake", ConsoleType.PC, 12.99, true);
        customer.addTransaction(t1);
        customer.addTransaction(t2);

        assertEquals(2, getTransactionCount(customer),
                "Customer should have 2 transactions recorded");
    }

    //  Test 5 — toString format
    @Test
    void testToStringFormat() {
        customer.setDiscount(true);
        String result = customer.toString();
        assertTrue(result.contains("💸 [10% Discount]"),
                "Customer toString() should include discount icon when applicable");
    }

    // --- Helper method to check internal transaction list size ---
    private int getTransactionCount(Customer customer) {
        try {
            var field = Customer.class.getDeclaredField("transactions");
            field.setAccessible(true);
            List<?> list = (List<?>) field.get(customer);
            return list.size();
        } catch (Exception e) {
            fail("Failed to access transactions list: " + e.getMessage());
            return 0;
        }
    }
}
