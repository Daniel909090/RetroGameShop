package org.example;

public interface Sellable {
    double calculatePrice();  // price logic (e.g. with discounts)
    void sell();              // perform sale action
}
