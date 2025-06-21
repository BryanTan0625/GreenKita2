package com.example.greenkita2;

import java.util.Scanner;

public class ProfilePage {

    public static void showProfile(User user) {
        System.out.println("\n===== Your Profile =====");
        System.out.println("Username: " + user.username);
        System.out.println("Name    : " + user.name);
        System.out.println("Age     : " + user.age);
        System.out.println("Gender  : " + user.gender);
        System.out.println("Bio     : " + user.bio);
    }

    public static void updateProfile(User user, Scanner scanner) {
        System.out.println("\n===== Update Profile =====");
        System.out.print("Enter new name: ");
        user.name = scanner.nextLine();

        System.out.print("Enter new age: ");
        user.age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new gender: ");
        user.gender = scanner.nextLine();

        System.out.print("Enter new bio: ");
        user.bio = scanner.nextLine();

        System.out.println("Profile updated successfully.");
    }
}
