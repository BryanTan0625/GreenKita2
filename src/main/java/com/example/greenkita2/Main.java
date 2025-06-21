package com.example.greenkita2;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Welcome =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    currentUser = LoginRegister.login(users, scanner);
                    if (currentUser != null) mainPage();
                    break;
                case 2:
                    LoginRegister.register(users, scanner);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void mainPage() {
        int option;
        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Profile Page");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    profilePage();
                    break;
                case 2:
                    System.out.println("Logging out...");
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (currentUser != null);
    }

    public static void profilePage() {
        int choice;
        do {
            System.out.println("\n===== Profile Menu =====");
            System.out.println("1. Show Profile");
            System.out.println("2. Update Profile");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ProfilePage.showProfile(currentUser);
                    break;
                case 2:
                    ProfilePage.updateProfile(currentUser, scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }
}
