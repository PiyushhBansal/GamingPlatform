
Gaming Platform
A console-based Java application simulating a gaming platform where players can:

Create & manage player profiles

Join tournaments

Trade virtual items between players

View analytics (popular games, economy stats, revenue, etc.)

This project uses MySQL as the backend database.

Features
Player Management – Create profiles & store preferences.

Tournaments – Register teams for tournaments.

Virtual Economy – Trade virtual items (buy/sell).

Analytics – Generate insights like popular games, revenue, economy stats.

Setup & Installation
1. Requirements
Java 17+

MySQL Server (running locally)

2. Database Setup
Start MySQL server.

Open terminal:

bash
Copy
Edit
mysql -u root -p
Create and import schema:

sql
Copy
Edit
CREATE DATABASE gaming_platform;
USE gaming_platform;
SOURCE schema.sql;
3. Configure Database Connection
Edit src/db/DBConnection.java if needed:

java
Copy
Edit
private static final String URL = "jdbc:mysql://localhost:3306/gaming_platform";
private static final String USER = "root";
private static final String PASSWORD = "your_password_here";
4. Run the Application
From terminal:

bash
Copy
Edit
java -jar GamingPlatform_Final.jar
Contents
GamingPlatform_Final.jar – Compiled runnable application

schema.sql – MySQL schema (import before running)

src/ – Full Java source code

mysql-connector-java-8.0.33.jar – MySQL driver included

Usage Example
mathematica
Copy
Edit
=== Welcome to Gaming Platform ===
Enter Player ID (simulate login): 1

=== MAIN MENU ===
1. Player Profiles
2. Tournaments
3. Virtual Items
4. Analytics
5. Exit
Choose: 3
Enter Receiver ID: 2
Enter Item ID: 1
Enter Quantity: 3
Enter Transaction Type ID: 4
Enter Value: 5
Author
Piyush Bansal

