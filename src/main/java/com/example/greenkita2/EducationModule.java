package com.example.greenkita2;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class EducationModule {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "user_data.txt";
    private static final String currentUser = "ACC123";

    private static Map<String, UserData> userDataMap = new HashMap<>();

    public static void main(String[] args) {
        loadUserData();
        userDataMap.putIfAbsent(currentUser, new UserData(currentUser));

        while (true) {
            resetDailyLimitsIfNeeded(currentUser);

            System.out.println("\n=== Education Module ===");
            System.out.println("1. Watch Educational Video");
            System.out.println("2. Take Quiz");
            System.out.println("3. Refer a Friend");
            System.out.println("4. Coin Summary");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> watchVideo();
                case 2 -> takeQuiz();
                case 3 -> referFriend();
                case 4 -> showCoinSummary();
                case 5 -> {
                    saveUserData();
                    System.out.println("✅ Exiting. Data saved.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void watchVideo() {
        UserData data = userDataMap.get(currentUser);

        while (true) {
            resetDailyLimitsIfNeeded(currentUser);

            if (data.videoWatchedToday >= 3) {
                System.out.println("❌ You’ve reached today’s video limit (3 per day).");
                System.out.print("Press Enter to return...");
                scanner.nextLine();
                return;
            }

            List<String> videos = new ArrayList<>(List.of(
                    "The Impact of Plastic Pollution",
                    "Renewable Energy Explained",
                    "How to Reduce Carbon Footprint",
                    "The Future of Sustainable Farming",
                    "Sustainable Living Tips"
            ));

            Collections.shuffle(videos);
            String selectedVideo = videos.get(0);
            int duration = 90;
            double totalCoins = 0.0;

            System.out.println("\n▶ Now watching: " + selectedVideo);
            System.out.println("Watch simulation begins (90 seconds in 30-sec blocks).");
            System.out.println("⚠️ Press ENTER any time to stop early. Partial coins will be rewarded and video will count.");

            for (int i = 1; i <= 3; i++) {
                System.out.printf("... Watching block %d/3 (%d seconds)...\n", i, i * 30);
                try {
                    for (int j = 0; j < 3; j++) {
                        if (System.in.available() > 0) {
                            scanner.nextLine();
                            throw new InterruptedException();
                        }
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException | IOException e) {
                    System.out.printf("⏹️ Video stopped early. You earned %.2f coins. It counts as 1 video today.\n", totalCoins);
                    data.addCoins(totalCoins, "Partially watched: " + selectedVideo);
                    data.videoWatchedToday++;
                    break;
                }
                totalCoins += 0.05;
                System.out.printf("... Earned: %.2f coins so far ...\n", totalCoins);
            }

            if (totalCoins == 0.15) {
                System.out.printf("🎉 Finished full video! Earned %.2f coins.\n", totalCoins);
                data.addCoins(totalCoins, "Watched: " + selectedVideo);
                data.videoWatchedToday++;
            }

            if (data.videoWatchedToday >= 3) {
                System.out.println("✅ Daily video limit reached.");
                System.out.print("Press Enter to return...");
                scanner.nextLine();
                return;
            }

            System.out.print("Do you want to watch another video? (yes/back): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) break;
        }
    }

    private static void takeQuiz() {
        UserData data = userDataMap.get(currentUser);

        while (true) {
            resetDailyLimitsIfNeeded(currentUser);

            if (data.quizTakenToday >= 2) {
                System.out.println("❌ You’ve reached today’s quiz limit (2 per day).");
                System.out.print("Press Enter to return...");
                scanner.nextLine();
                return;
            }

            class Question {
                String q;
                String[] opts;
                int correct;

                Question(String q, String[] opts, int correct) {
                    this.q = q;
                    this.opts = opts;
                    this.correct = correct;
                }
            }

            List<Question> questions = new ArrayList<>(List.of(
                    new Question("Which is a renewable energy source?", new String[]{"1. Coal", "2. Solar", "3. Diesel"}, 2),
                    new Question("What gas causes global warming?", new String[]{"1. Oxygen", "2. Carbon Dioxide", "3. Nitrogen"}, 2),
                    new Question("What reduces plastic waste?", new String[]{"1. Plastic straws", "2. Reusable bags", "3. Bottled water"}, 2),
                    new Question("Main benefit of planting trees?", new String[]{"1. Noise", "2. Absorb CO₂", "3. Plastic"}, 2),
                    new Question("Which saves energy?", new String[]{"1. Lights on", "2. LED bulbs", "3. AC with windows open"}, 2)
            ));

            Collections.shuffle(questions);
            Question selected = questions.get(0);

            System.out.println("❓ " + selected.q);
            for (String opt : selected.opts) System.out.println(opt);
            System.out.print("Your answer (1-3): ");
            int answer = scanner.nextInt();
            scanner.nextLine();

            if (answer == selected.correct) {
                double coins = 0.05;
                data.addCoins(coins, "Quiz: " + selected.q);
                data.quizTakenToday++;
                System.out.printf("✅ Correct! You earned %.2f coins.\n", coins);
            } else {
                System.out.println("❌ Incorrect. No coins earned.");
            }

            if (data.quizTakenToday >= 2) {
                System.out.println("✅ Quiz limit reached.");
                System.out.print("Press Enter to return...");
                scanner.nextLine();
                return;
            }

            System.out.print("Take another quiz? (yes/back): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) break;
        }
    }

    private static void referFriend() {
        UserData data = userDataMap.get(currentUser);

        System.out.println("\n🔗 Refer a Friend");
        System.out.printf("Your referral link: https://eduapp.com/ref/%s\n", currentUser);

        while (true) {
            System.out.print("Enter your friend's account ID (or type 'back' to return): ");
            String friendId = scanner.nextLine().trim();

            if (friendId.equalsIgnoreCase("back")) return;

            if (friendId.equalsIgnoreCase(currentUser)) {
                System.out.println("⚠️ You cannot refer yourself.");
            } else if (!userDataMap.containsKey(friendId)) {
                System.out.println("❌ That account ID doesn't exist.");
            } else {
                UserData referrer = userDataMap.get(friendId);
                double bonus = 0.30;

                referrer.addCoins(bonus, "Referral bonus (referred " + currentUser + ")");
                data.addCoins(bonus, "Referred by " + friendId);
                System.out.printf("✅ Referral complete. You and %s both earned %.2f coins.\n", friendId, bonus);
                return;
            }
        }
    }

    private static void showCoinSummary() {
        UserData data = userDataMap.get(currentUser);

        System.out.println("\n💰 Coin Summary");
        System.out.printf("Total Coins: %.2f\n", data.totalCoins);
        System.out.println("Breakdown:");
        for (String entry : data.earnings) {
            System.out.println("- " + entry);
        }

        System.out.print("Press Enter to return...");
        scanner.nextLine();
    }

    static class UserData {
        String userId;
        double totalCoins;
        List<String> earnings = new ArrayList<>();
        LocalDate lastActiveDate;
        int videoWatchedToday = 0;
        int quizTakenToday = 0;

        UserData(String userId) {
            this.userId = userId;
            this.totalCoins = 0;
            this.lastActiveDate = LocalDate.now();
        }

        void addCoins(double amount, String source) {
            totalCoins += amount;
            earnings.add(String.format("%.2f coins - %s", amount, source));
        }
    }

    private static void saveUserData() {
        // Read existing data into a map
        Map<String, String> fileLines = new HashMap<>();

        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String userId = line.split(";")[0];
                    fileLines.put(userId, line);
                }
            } catch (IOException e) {
                System.out.println("❌ Error reading existing data: " + e.getMessage());
            }
        }

        // Update the current user's line
        UserData current = userDataMap.get(currentUser);
        String updatedLine = String.format("%s;%f;%s;%d;%d;%s",
                current.userId,
                current.totalCoins,
                String.join("|", current.earnings),
                current.videoWatchedToday,
                current.quizTakenToday,
                current.lastActiveDate.toString()
        );
        fileLines.put(currentUser, updatedLine);

        // Rewrite all data (preserving others)
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (String line : fileLines.values()) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving updated user data: " + e.getMessage());
        }
    }

    private static void loadUserData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 6) continue;

                UserData user = new UserData(parts[0]);
                user.totalCoins = Double.parseDouble(parts[1]);
                user.earnings = new ArrayList<>(List.of(parts[2].split("\\|")));
                user.videoWatchedToday = Integer.parseInt(parts[3]);
                user.quizTakenToday = Integer.parseInt(parts[4]);
                user.lastActiveDate = LocalDate.parse(parts[5]);

                userDataMap.put(user.userId, user);
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        }
    }

    private static void resetDailyLimitsIfNeeded(String userId) {
        UserData data = userDataMap.get(userId);
        if (!data.lastActiveDate.equals(LocalDate.now())) {
            data.videoWatchedToday = 0;
            data.quizTakenToday = 0;
            data.lastActiveDate = LocalDate.now();
        }
    }
}



