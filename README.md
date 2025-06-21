🌱 GreenKita
GreenKita2 is a desktop green fintech application that rewards users with digital coins for participating in environmentally friendly actions such as recycling and using green transport. 
The application aims to encourage sustainable habits through gamified tracking and a simple rewards system.

👥 Team Members
Tan Jun Xuan
Ng Waii Hiin 
Tang Keng Hin
Tan Jun Shen
Yee Ji Jien


🛠 Technologies Used
Java 24 – Core language
JavaFX 17.0.6 – For building the user interface
Maven – Project and dependency management
FormsFX – UI forms and layout handling
JUnit 5.10.2 – Unit testing framework
Text Files (.txt) – Local data storage for balance, logs, and history

⚙️ Challenge and Approach
The main challenge was to design a lightweight application that could motivate users to engage in real-life sustainable actions, without requiring complex databases or server integrations.
Our approach:
Use local file storage to keep the app simple and offline-friendly.
Provide a clear reward system where coins are earned based on the number of bottles recycled or the kilometers traveled using green transport.
Structure the app in a modular way to allow for future expansions, such as API integration, leaderboards, and user profiles.
We focused on keeping the codebase clean and using standard JavaFX components so that it is accessible for further development or educational use.
▶️ Usage Instructions
1. System Requirements
Java 24
Maven 3.6 or later
Internet connection (optional, for future API features)
2. Running the Application
bash
Copy
Edit
git clone https://github.com/BryanTan0625/GreenKita2.git
cd GreenKita2
mvn clean javafx:run

4. Key Files
mileage_log.txt – Records transport mileage
recycle_history.txt – Logs recycled item activity
balance.txt – Shows current coin balance
transactions.txt – Records top-ups and earned coins
activism_data.txt – (Reserved for future use)


🧩 Future Enhancements
Integrate carbon footprint APIs for more accurate tracking
Introduce community leaderboard
Add QR scanning for smart bin verification and more


🔒 Privacy Policy
All data is stored locally. No user information is collected or sent online. Any future integrations will remain optional and transparent to the user.

🤝 License and Use
This project is for academic and demo purposes. You may use and modify the code for learning, but redistribution or commercial use is not allowed without permission.
