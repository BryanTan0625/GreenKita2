package com.example.greenkita2;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private final List<CoinTransaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(CoinTransaction tx) {
        transactions.add(tx);
    }

    public void printAll() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (CoinTransaction tx : transactions) {
                System.out.println(tx);
            }
        }
    }

    public void printTopUps() {
        boolean found = false;
        for (CoinTransaction tx : transactions) {
            if (tx.getType().equals("TOPUP")) {
                System.out.println(tx);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No top-up transactions found.");
        }
    }
}
