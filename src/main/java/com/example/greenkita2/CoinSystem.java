package com.example.greenkita2;

import java.util.Scanner;

public class CoinSystem {
    static Scanner scanner = new Scanner(System.in);
    static Wallet wallet = new Wallet();

    public static void main(String[] args) {
        int choice;
        do {
            showMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1 -> topUpMenu();
                case 2 -> rewardsMenu();
                case 3 -> System.out.println("Current Balance: " + wallet.getBalance());
                case 4 -> viewTransactionMenu();
                case 5 -> paymentMenu();
                case 0 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    static void showMainMenu() {
        System.out.println("\n====== COIN SYSTEM MENU ======");
        System.out.println("1. Top Up Coins");
        System.out.println("2. Reward");
        System.out.println("3. View Coin Balance");
        System.out.println("4. View Transactions");
        System.out.println("5. Make a Payment");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    static void topUpMenu() {
        int option;
        do {
            System.out.println("\n--- Top-Up Menu ---");
            System.out.println("1. Online Banking");
            System.out.println("2. E-Wallet");
            System.out.println("3. Credit/Debit Card");
            System.out.println("4. Redeem PIN Code");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select top-up method: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Enter amount to top up via Online Banking: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    wallet.topUp(amount, "Top-up via Online Banking");
                    System.out.println("Top-up successful via Online Banking!");
                    option = 0;
                }
                case 2 -> {
                    System.out.print("Enter amount to top up via E-Wallet: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    wallet.topUp(amount, "Top-up via E-Wallet");
                    System.out.println("Top-up successful via E-Wallet!");
                    option = 0;
                }
                case 3 -> {
                    System.out.print("Enter amount to top up via Credit/Debit Card: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    wallet.topUp(amount, "Top-up via Credit/Debit Card");
                    System.out.println("Top-up successful via Credit/Debit Card!");
                    option = 0;
                }
                case 4 -> {
                    System.out.print("Enter PIN code (e.g., PIN50, PIN100): ");
                    String pin = scanner.nextLine().toUpperCase();
                    int pinAmount = switch (pin) {
                        case "PIN50" -> 50;
                        case "PIN100" -> 100;
                        default -> 0;
                    };
                    if (pinAmount > 0) {
                        wallet.topUp(pinAmount, "Top-up via PIN Code (" + pin + ")");
                        System.out.println("PIN code applied: " + pinAmount + " coins added.");
                        option = 0;
                    } else {
                        System.out.println("Invalid PIN code.");
                    }
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }



    static void rewardsMenu() {
        int option;
        do {
            System.out.println("\n--- Rewards Menu ---");
            System.out.println("1. Redeem Food (30 coins)");
            System.out.println("2. Redeem Merchandise (50 coins)");
            System.out.println("3. Redeem Gift Card (70 coins)");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose reward: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    if (wallet.spend(30, "Redeemed: Food")) {
                        System.out.println("You redeemed Food!");
                    } else {
                        System.out.println("Not enough coins.");
                    }
                    option = 0;
                }
                case 2 -> {
                    if (wallet.spend(50, "Redeemed: Merchandise")) {
                        System.out.println("You redeemed Merchandise!");
                    } else {
                        System.out.println("Not enough coins.");
                    }
                    option = 0;
                }
                case 3 -> {
                    if (wallet.spend(70, "Redeemed: Gift Card")) {
                        System.out.println("You redeemed a Gift Card!");
                    } else {
                        System.out.println("Not enough coins.");
                    }
                    option = 0;
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }


    static void viewTransactionMenu() {
        int option;
        do {
            System.out.println("\n--- View Transactions Menu ---");
            System.out.println("1. View Top-Up Transactions Only");
            System.out.println("2. View All Transactions");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> wallet.printTopUpTransactions();
                case 2 -> wallet.printAllTransactions();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);
    }

    static void paymentMenu() {
        int option;
        do {
            System.out.println("\n--- Payment Menu ---");
            System.out.println("1. Make a Payment");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Enter recipient's name: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter amount to pay: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();

                    if (wallet.spend(amount, "Payment to " + recipient)) {
                        System.out.println("Payment of " + amount + " coins to " + recipient + " successful.");
                    } else {
                        System.out.println("Payment failed: Not enough balance.");
                    }
                    option = 0; // auto-return
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }
}