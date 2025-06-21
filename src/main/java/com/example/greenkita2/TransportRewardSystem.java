package com.example.greenkita2;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class User {
    String name;
    int mileage;
    int coins;
    int weeklyRides;
    int commuteStreak;
    int referrals;
    int socialShares;

    public User(String name) {
        this.name = name;
        this.mileage = 0;
        this.coins = 0;
        this.weeklyRides = 0;
        this.commuteStreak = 0;
        this.referrals = 0;
        this.socialShares = 0;
    }

    public void addMileage(int km) {
        mileage += km;
        int earned = (km / 100) * 2;
        coins += earned;

        System.out.println("✅ Earned " + earned + " coins for " + km + " km.");
        saveMileageToFile(km);
    }


    public void completeWeeklyChallenge() {
        if (weeklyRides >= 5) {
            coins += 3;
            System.out.println("✅ Weekly Challenge Completed! +3 coins.");
        } else {
            System.out.println("❌ Not enough rides to complete weekly challenge.");
        }
    }

    public void updateStreak() {
        if (commuteStreak % 10 == 0 && commuteStreak > 0) {
            coins += 5;
            System.out.println("🏆 10-day commute streak achieved! +5 coins.");
        } else {
            System.out.println("✅ Commute streak updated: " + commuteStreak + " days.");
        }
    }

    public void referFriend() {
        referrals++;
        coins += 2;
        System.out.println("👥 Friend referred. +2 coins.");
    }

    public void shareOnSocial() {
        if (socialShares < 3) {
            socialShares++;
            coins += 1;
            System.out.println("📣 Shared on social. +1 coin.");
        } else {
            System.out.println("⚠️ Weekly share limit reached.");
        }
    }

    public String getTier() {
        if (mileage >= 100000) return "Carbon Hero";
        else if (mileage >= 101) return "Eco Voyager";
        else if (mileage >= 500) return "Sustainability Advocate";
        else if (mileage >= 2500) return "Planet Protector";

        else return "Green Explorer";
    }

    public void showStatus() {
        System.out.println("\n----- " + name + "'s Status -----");
        System.out.println("Mileage: " + mileage + " km");
        System.out.println("Coins: " + coins);
        System.out.println("Tier: " + getTier());
        System.out.println("Referrals: " + referrals);
        System.out.println("Commute Streak: " + commuteStreak);
        System.out.println("-------------------------------");
    }

    public void showMileageHistory() {
        System.out.println("\n📄 Mileage History:");

        try (Scanner fileReader = new Scanner(new java.io.File("mileage_log.txt"))) {
            boolean found = false;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                System.out.println(line);
                found = true;
            }
            if (!found) {
                System.out.println("🚫 No mileage records found.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error reading mileage history: " + e.getMessage());
        }
    }

    private void saveMileageToFile(int km) {
        LocalDate date = LocalDate.now();
        String today = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String line = today + ": +" + km + " km\n";

        try (FileWriter fw = new FileWriter("mileage_log.txt", true)) {
            fw.write(line);
        } catch (IOException e) {
            System.out.println("❌ Error writing to mileage log: " + e.getMessage());
        }
    }

}


public class TransportRewardSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name to start: ");
        String username = scanner.nextLine();
        User user = new User(username);

        int choice;

        do {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add mileage");
            System.out.println("2. Complete weekly challenge");
            System.out.println("3. Update commute streak");
            System.out.println("4. Refer a friend");
            System.out.println("5. Share on social media");
            System.out.println("6. Show status");
            System.out.println("7. Show Mileage");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter km to add: ");
                    int km = scanner.nextInt();
                    user.addMileage(km);
                    break;


                case 2:
                    System.out.print("Enter number of public transport rides this week: ");
                    user.weeklyRides = scanner.nextInt();
                    user.completeWeeklyChallenge();
                    break;

                case 3:
                    System.out.print("Enter current commute streak (days): ");
                    user.commuteStreak = scanner.nextInt();
                    user.updateStreak();
                    break;

                case 4:
                    user.referFriend();
                    break;

                case 5:
                    user.shareOnSocial();
                    break;

                case 6:
                    user.showStatus();
                    break;

                case 7:
                    user.showMileageHistory();
                    break;

                case 0:
                    System.out.println("Goodbye! 🌿 Keep riding green.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
