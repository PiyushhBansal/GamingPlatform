import dao.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlayerDAO playerDAO = new PlayerDAO();
        PlayerProfileDAO profileDAO = new PlayerProfileDAO();
        TournamentDAO tournamentDAO = new TournamentDAO();
        ItemTransactionDAO transactionDAO = new ItemTransactionDAO();
        AnalyticsDAO analyticsDAO = new AnalyticsDAO();

        System.out.println("=== Welcome to Gaming Platform ===");
        System.out.print("Enter Player ID (simulate login): ");
        String playerId = scanner.nextLine();

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Player Profiles");
            System.out.println("2. Tournaments");
            System.out.println("3. Virtual Items");
            System.out.println("4. Analytics");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Game ID to create profile: ");
                    String gameId = scanner.nextLine();
                    profileDAO.createProfile(playerId, gameId, "Default Prefs", "avatar.png", 100.0);
                }
                case 2 -> {
                    System.out.print("Enter Tournament ID: ");
                    String tId = scanner.nextLine();
                    System.out.print("Enter Team ID: ");
                    String teamId = scanner.nextLine();
                    tournamentDAO.registerTeamToTournament(tId, teamId);
                }
                case 3 -> {
                    System.out.print("Enter Receiver ID: ");
                    String receiverId = scanner.nextLine();
                    System.out.print("Enter Item ID: ");
                    String itemId = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Enter Transaction Type ID: ");
                    int typeId = scanner.nextInt();
                    System.out.print("Enter Value: ");
                    double value = scanner.nextDouble();
                    scanner.nextLine();
                    transactionDAO.recordTransaction(playerId, receiverId, itemId, qty, typeId, value);
                }
                case 4 -> {
                    System.out.println("\n=== Analytics Menu ===");
                    System.out.println("1. Player Skill Progression");
                    System.out.println("2. Popular Games by Region");
                    System.out.println("3. Tournament Participation");
                    System.out.println("4. Virtual Economy Stats");
                    System.out.println("5. Cheating Reports");
                    System.out.println("6. Revenue Stats");
                    System.out.print("Choose: ");
                    int aChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (aChoice) {
                        case 1 -> analyticsDAO.getPlayerSkillProgression(playerId);
                        case 2 -> analyticsDAO.getPopularGamesByRegion();
                        case 3 -> analyticsDAO.getTournamentParticipation();
                        case 4 -> analyticsDAO.getVirtualEconomyStats();
                        case 5 -> analyticsDAO.getCheatingReports();
                        case 6 -> analyticsDAO.getRevenueStats();
                        default -> System.out.println("Invalid choice!");
                    }
                }
                case 5 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
