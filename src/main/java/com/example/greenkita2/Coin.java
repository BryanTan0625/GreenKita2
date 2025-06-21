package com.example.greenkita2;

public class Coin {
    private int balance;

    public Coin() {
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void add(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean deduct(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
