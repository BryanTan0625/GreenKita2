package com.example.greenkita2;

import java.util.HashMap;
import java.util.Scanner;

public class LoginRegister {
    public static User login(HashMap<String, User> users, Scanner scanner) {
        System.out.println("\n===== Login =====");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.password.equals(password)) {
                System.out.println("Login successful.");
                return user;
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("User not found. Please register first.");
        }

        return null;
    }

    public static void register(HashMap<String, User> users, Scanner scanner) {
        System.out.println("\n===== Register =====");
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try another.");
            return;
        }

        System.out.print("Your name: ");
        String name = scanner.nextLine();

        System.out.print("Your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Your gender: ");
        String gender = scanner.nextLine();

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        System.out.print("Short bio: ");
        String bio = scanner.nextLine();

        User newUser = new User(username, name, age, gender, password, bio);
        users.put(username, newUser);

        System.out.println("Registration successful. Please login.");
    }
}
