package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppManagerTest {

    private AppManager appManager;

    @BeforeEach
    void setUp() {
        appManager = new AppManager();
    }

    //  Test 1 — Ensure Inventory is created properly
    @Test
    void testInventoryInitialization() {
        Inventory inv = appManager.getInventory();
        assertNotNull(inv, "Inventory should not be null");
        assertFalse(inv.getGames().isEmpty(), "Inventory should contain preloaded games");
    }

    //  Test 2 — Ensure customer list starts empty
    @Test
    void testInitialCustomerListIsEmpty() throws Exception {
        ArrayList<Customer> customers = getPrivateCustomerList(appManager);
        assertTrue(customers.isEmpty(), "Customer list should start empty");
    }

    //  Test 3 — Ensure getOrCreateCustomer adds new customer
    @Test
    void testGetOrCreateCustomerAddsNew() throws Exception {
        Customer c1 = invokeGetOrCreateCustomer(appManager, "John", "Glasgow");
        assertNotNull(c1, "Customer should be created");
        ArrayList<Customer> customers = getPrivateCustomerList(appManager);
        assertEquals(1, customers.size(), "Customer list should now contain one customer");
    }

    //  Test 4 — Ensure getOrCreateCustomer returns existing customer
    @Test
    void testGetOrCreateCustomerReturnsExisting() throws Exception {
        Customer c1 = invokeGetOrCreateCustomer(appManager, "John", "Glasgow");
        Customer c2 = invokeGetOrCreateCustomer(appManager, "John", "Glasgow");
        assertSame(c1, c2, "Method should return the same customer instance if it already exists");
    }

    //  Test 5 — Ensure displayAllCustomers doesn’t throw
    @Test
    void testDisplayAllCustomersDoesNotThrow() {
        assertDoesNotThrow(() -> {
            appManager.getInventory().displayInventory();
        }, "Displaying inventory should not throw exceptions");
    }

    //  Test 6 — Ensure multiple customers are added properly
    @Test
    void testAddMultipleCustomers() throws Exception {
        invokeGetOrCreateCustomer(appManager, "Alice", "London");
        invokeGetOrCreateCustomer(appManager, "Bob", "Edinburgh");
        invokeGetOrCreateCustomer(appManager, "Charlie", "Aberdeen");

        ArrayList<Customer> customers = getPrivateCustomerList(appManager);
        assertEquals(3, customers.size(), "Should store all distinct customers");
    }

    // --- 🔧 Reflection helpers ---

    @SuppressWarnings("unchecked")
    private ArrayList<Customer> getPrivateCustomerList(AppManager app) throws Exception {
        Field field = AppManager.class.getDeclaredField("customers");
        field.setAccessible(true);
        return (ArrayList<Customer>) field.get(app);
    }

    private Customer invokeGetOrCreateCustomer(AppManager app, String name, String address) throws Exception {
        var method = AppManager.class.getDeclaredMethod("getOrCreateCustomer", String.class, String.class);
        method.setAccessible(true);
        return (Customer) method.invoke(app, name, address);
    }
}
