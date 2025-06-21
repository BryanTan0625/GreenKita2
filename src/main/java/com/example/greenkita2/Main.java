package com.example.greenkita2;

import com.example.greenkita2.HackathonActivism.src.Activism;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("🌱 Welcome to GreenKita2 - Sustainable Living Platform!");

        while (true) {
            System.out.println("\n===== Welcome =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (option) {
                    case 1:
                        currentUser = LoginRegister.login(users, scanner);
                        if (currentUser != null) {
                            mainPage();
                        }
                        break;
                    case 2:
                        LoginRegister.register(users, scanner);
                        break;
                    case 0:
                        System.out.println("Goodbye! Thanks for using GreenKita2! 🌱");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    public static void mainPage() {
        int option;
        do {
            System.out.println("\n===== Main Dashboard - " + currentUser.name + " =====");
            System.out.println("1. Profile Management");
            System.out.println("2. Coin System & Wallet");
            System.out.println("3. Transport Rewards");
            System.out.println("4. Recycle & Upcycle (GreenKit)");
            System.out.println("5. Education Module");
            System.out.println("6. Activism");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (option) {
                    case 1:
                        profilePage();
                        break;
                    case 2:
                        coinSystemPage();
                        break;
                    case 3:
                        TransportRewardSystem.main(currentUser.name);
                        break;
                    case 4:
                        GreenKit.runRecycleModule(currentUser.name);
                        break;
                    case 5:
                        educationModulePage();
                        break;
                    case 6:
                        Activism.main();
                        break;


                    case 0:
                        System.out.println("Logging out... See you soon, " + currentUser.name + "!");
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
                option = -1; // continue the loop
            }
        } while (currentUser != null);
    }

    public static void profilePage() {
        int choice;
        do {
            System.out.println("\n===== Profile Menu =====");
            System.out.println("1. Show Profile");
            System.out.println("2. Update Profile");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        ProfilePage.showProfile(currentUser);
                        break;
                    case 2:
                        ProfilePage.updateProfile(currentUser, scanner);
                        break;
                    case 0:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    public static void coinSystemPage() {
        System.out.println("\n🪙 Launching Coin System for " + currentUser.name + "...");

        // Create a new scanner for CoinSystem to avoid conflicts
        Scanner coinScanner = new Scanner(System.in);
        CoinSystem.wallet = new Wallet(); // Reset wallet for current user

        int choice;
        do {
            System.out.println("\n====== COIN SYSTEM MENU ======");
            System.out.println("1. Top Up Coins");
            System.out.println("2. Reward");
            System.out.println("3. View Coin Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Make a Payment");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                choice = coinScanner.nextInt();
                coinScanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> CoinSystem.topUpMenu();
                    case 2 -> CoinSystem.rewardsMenu();
                    case 3 -> System.out.println("Current Balance: " + CoinSystem.wallet.getBalance());
                    case 4 -> CoinSystem.viewTransactionMenu();
                    case 5 -> CoinSystem.paymentMenu();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                coinScanner.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    public static void educationModulePage() {
        System.out.println("\n📚 Launching Education Module for " + currentUser.name + "...");
        System.out.println("Note: This will run the education module with user ID: " + currentUser.name);

        // Run the education module
        EducationModule.main(new String[]{});
    }
}