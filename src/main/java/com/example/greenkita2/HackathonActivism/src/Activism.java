package com.example.greenkita2.HackathonActivism.src;

import com.example.greenkita2.Main;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Activism {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "activism_data.txt";
    private static final String currentUser = "ACC123";

    private static Map<String, List<ActivityProof>> userSubmissions = new HashMap<>();

    public static void main() {
        loadSubmissions();

        while (true) {
            System.out.println("\n=== 🌱 EcoActivism Hub ===");
            System.out.println("1. Join Tree Planting Event");
            System.out.println("2. Join CleanUp Camp");
            System.out.println("3. Volunteer with NGO");
            System.out.println("4. View My Submissions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> uploadProof("Tree Planting");
                case "2" -> uploadProof("CleanUp Camp");
                case "3" -> uploadProof("Volunteer with NGO");
                case "4" -> viewMySubmissions();
                case "5" -> {
                    saveSubmissions();
                    System.out.println("✅ Thank you for taking action!");

                    Main.mainPage();

                    return;
                }
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private static void uploadProof(String activityName) {
        System.out.println("\n📸 Upload image proof for: " + activityName);
        System.out.print("Enter image file path (or type 'cancel' to return): ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("cancel")) {
            System.out.println("↩️ Upload cancelled. Returning to menu.");
            return;
        }

        File file = new File(input);
        if (!file.exists() || file.isDirectory()) {
            System.out.println("❌ File does not exist or is a folder.");
            return;
        }

        ActivityProof proof = new ActivityProof(activityName, file.getAbsolutePath(), new Date());
        userSubmissions.computeIfAbsent(currentUser, k -> new ArrayList<>()).add(proof);

        System.out.println("✅ Proof submitted successfully!");
    }

    private static void viewMySubmissions() {
        System.out.println("\n📂 Your Submissions:");
        List<ActivityProof> proofs = userSubmissions.getOrDefault(currentUser, new ArrayList<>());

        if (proofs.isEmpty()) {
            System.out.println("⚠️ No submissions yet.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (ActivityProof proof : proofs) {
                System.out.printf("- %s | Submitted: %s | File: %s\n",
                        proof.activityName,
                        sdf.format(proof.timestamp),
                        proof.imagePath);
            }
        }

        System.out.print("Press Enter to return...");
        scanner.nextLine();
    }

    private static void saveSubmissions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Map.Entry<String, List<ActivityProof>> entry : userSubmissions.entrySet()) {
                String userId = entry.getKey();
                for (ActivityProof proof : entry.getValue()) {
                    writer.printf("%s;%s;%s;%d\n", userId, proof.activityName, proof.imagePath, proof.timestamp.getTime());
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving submissions: " + e.getMessage());
        }
    }

    private static void loadSubmissions() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 4) continue;

                String userId = parts[0];
                String activity = parts[1];
                String imagePath = parts[2];
                long timestamp = Long.parseLong(parts[3]);

                ActivityProof proof = new ActivityProof(activity, imagePath, new Date(timestamp));
                userSubmissions.computeIfAbsent(userId, k -> new ArrayList<>()).add(proof);
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading submissions: " + e.getMessage());
        }
    }



    static class ActivityProof {
        String activityName;
        String imagePath;
        Date timestamp;

        ActivityProof(String activityName, String imagePath, Date timestamp) {
            this.activityName = activityName;
            this.imagePath = imagePath;
            this.timestamp = timestamp;
        }
    }
}

