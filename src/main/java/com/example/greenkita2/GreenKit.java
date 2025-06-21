package com.example.greenkita2; // Fixed package name

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class GreenKit {

    static final String MACHINE_ADDRESS = "GreenKita Machine Location: Lot 12, Jalan Hijau, Eco Park, Kuala Lumpur";

    static int totalBottles = 0;
    static int totalItems = 0;
    static int totalCoins = 0;
    static double co2Saved = 0.0;
    static Random random = new Random();

    static List<HistoryEntry> history = new ArrayList<>();
    static Map<String, Integer> userLeaderboard = new HashMap<>();

    static class HistoryEntry {
        String date;
        String action;
        String detail;
        int coins;

        HistoryEntry(String date, String action, String detail, int coins) {
            this.date = date;
            this.action = action;
            this.detail = detail;
            this.coins = coins;
        }

        @Override
        public String toString() {
            return date + " | " + action + " | " + detail + " | +" + coins + " coins";
        }
    }

    public static void runRecycleModule(String username) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("♻️ Welcome to the Recycle & Upcycle Console App!");
        System.out.println(MACHINE_ADDRESS);
        System.out.println("User: " + username);

        userLeaderboard.putIfAbsent(username, 0);

        do {
            System.out.println("\nChoose an action:");
            System.out.println("1. Record Bottles Recycled");
            System.out.println("2. Upcycle / Donate Item");
            System.out.println("3. Show Stats");
            System.out.println("4. Show History");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    recordBottles(scanner, username);
                    break;
                case "2":
                    upcycleOrDonate(scanner, username);
                    break;
                case "3":
                    showStats();
                    break;
                case "4":
                    showHistory();
                    break;
                case "0":
                    Main.mainPage();

                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (!input.equals("0"));
    }

    private static void recordBottles(Scanner scanner, String username) {
        try {
            System.out.print("Enter number of bottles: ");
            int bottles = Integer.parseInt(scanner.nextLine());
            if (bottles <= 0) {
                System.out.println("⚠️ Must be greater than 0.");
                return;
            }

            int coinsEarned = applyRandomBonus(bottles * 2);
            totalBottles += bottles;
            totalCoins += coinsEarned;
            co2Saved += bottles * 0.05;

            String date = LocalDate.now().toString();
            HistoryEntry entry = new HistoryEntry(date, "Recycle", bottles + " bottles", coinsEarned);
            history.add(entry);

            // Save entry to the file immediately
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("recycle_history_" + username + ".txt", true))) {
                writer.write(entry.toString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("⚠️ Failed to write history to file.");
            }

            System.out.println("✅ You earned +" + coinsEarned + " coins!");
            checkAchievements();

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid input. Please enter a number.");
        }
    }

    private static void upcycleOrDonate(Scanner scanner, String username) {
        System.out.print("Enter item name: ");
        String item = scanner.nextLine();
        System.out.print("Select action (Upcycled / Donated): ");
        String action = scanner.nextLine();

        if (item.isEmpty() || (!action.equalsIgnoreCase("Upcycled") && !action.equalsIgnoreCase("Donated"))) {
            System.out.println("⚠️ Invalid item or action.");
            return;
        }

        int coinsEarned = applyRandomBonus(50);
        totalItems++;
        totalCoins += coinsEarned;
        co2Saved += 0.3;
        userLeaderboard.put(username, userLeaderboard.get(username) + coinsEarned);

        String date = LocalDate.now().toString();
        HistoryEntry entry = new HistoryEntry(date, action, item, coinsEarned);
        history.add(entry);

        // Save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("recycle_history_" + username + ".txt", true))) {
            writer.write(entry.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("⚠️ Failed to write history to file.");
        }

        System.out.println("✅ You earned +" + coinsEarned + " coins!");
        checkAchievements();
    }

    private static int applyRandomBonus(int baseCoins) {
        if (random.nextInt(10) == 0) {
            System.out.println("🎁 Lucky Bonus! You got +20 extra coins!");
            return baseCoins + 20;
        }
        return baseCoins;
    }

    private static void checkAchievements() {
        if (totalBottles >= 100) {
            System.out.println("🏅 Achievement Unlocked: Bottle Hero (100+ bottles recycled)");
        }
        if (totalCoins >= 500) {
            System.out.println("💰 Achievement Unlocked: Eco Tycoon (500+ coins earned)");
        }
        if (totalItems >= 10) {
            System.out.println("🎉 Achievement Unlocked: Generous Giver (10+ items donated)");
        }
    }

    private static void showStats() {
        System.out.println("\n📊 Your Recycling Stats:");
        System.out.println("Total Bottles: " + totalBottles);
        System.out.println("Items Donated/Upcycled: " + totalItems);
        System.out.println("Coins Earned: " + totalCoins);
        System.out.printf("CO₂ Saved: %.2f kg\n", co2Saved);
        showCo2FunFact();
    }

    private static void showCo2FunFact() {
        if (co2Saved >= 5.0) {
            System.out.println("🌳 You've saved as much CO₂ as one tree absorbs in a month!");
        }
        if (co2Saved >= 20.0) {
            System.out.println("🚗 That's equal to avoiding a 100km car trip!");
        }
    }

    private static void showHistory() {
        System.out.println("\n🕓 History Log (from memory):");
        if (history.isEmpty()) {
            System.out.println("(No entries yet.)");
        } else {
            history.forEach(System.out::println);
        }
    }
}