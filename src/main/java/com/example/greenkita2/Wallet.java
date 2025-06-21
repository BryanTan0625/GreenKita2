package com.example.greenkita2;

import java.io.*;
import java.util.*;

public class Wallet {
    private final Coin coin;
    private final TransactionHistory history;

    private final String BALANCE_FILE = "balance.txt";
    private final String TRANSACTION_FILE = "transactions.txt";

    public Wallet() {
        this.coin = new Coin();
        this.history = new TransactionHistory();
        loadBalance();
        loadTransactions();
    }

    public void topUp(int amount, String description) {
        coin.add(amount);
        CoinTransaction tx = new CoinTransaction("TOPUP", amount, description);
        history.addTransaction(tx);
        saveBalance();
        appendTransaction(tx);
    }

    public boolean spend(int amount, String description) {
        if (coin.deduct(amount)) {
            CoinTransaction tx = new CoinTransaction("SPEND", amount, description);
            history.addTransaction(tx);
            saveBalance();
            appendTransaction(tx);
            return true;
        }
        return false;
    }

    public int getBalance() {
        return coin.getBalance();
    }

    public void printAllTransactions() {
        history.printAll();
    }

    public void printTopUpTransactions() {
        history.printTopUps();
    }

    // --- File I/O Methods ---

    private void saveBalance() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCE_FILE))) {
            writer.write(String.valueOf(coin.getBalance()));
        } catch (IOException e) {
            System.out.println("Error saving balance: " + e.getMessage());
        }
    }

    private void loadBalance() {
        File file = new File(BALANCE_FILE);
        if (file.exists()) {
            try (Scanner reader = new Scanner(file)) {
                if (reader.hasNextInt()) {
                    coin.add(reader.nextInt());
                }
            } catch (IOException e) {
                System.out.println("Error loading balance: " + e.getMessage());
            }
        }
    }

    private void appendTransaction(CoinTransaction tx) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(tx.getTimestamp() + ";" + tx.getType() + ";" + tx.getAmount() + ";" + tx.getDescription());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    private void loadTransactions() {
        File file = new File(TRANSACTION_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";", 4);
                    if (parts.length == 4) {
                        String timestamp = parts[0];
                        String type = parts[1];
                        int amount = Integer.parseInt(parts[2]);
                        String description = parts[3];

                        CoinTransaction tx = new CoinTransaction(type, amount, description);
                        history.addTransaction(tx);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading transactions: " + e.getMessage());
            }
        }
    }
}
