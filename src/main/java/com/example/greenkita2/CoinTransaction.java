package com.example.greenkita2;

import java.time.LocalDateTime;

public class CoinTransaction {
    private final LocalDateTime timestamp;
    private final String type; // "TOPUP" or "SPEND"
    private final int amount;
    private final String description;

    public CoinTransaction(String type, int amount, String description) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + ": " + amount + " (" + description + ")";
    }
}
