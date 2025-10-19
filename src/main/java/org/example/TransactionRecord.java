package org.example;

import java.time.LocalDateTime;

// ✅ Record for simple transaction data
public record TransactionRecord(
        String customerName,
        String gameTitle,
        ConsoleType console,
        double price,
        boolean tradeIn,
        LocalDateTime date
) {}
