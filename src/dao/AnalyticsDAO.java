package dao;

import db.DBConnection;
import java.sql.*;

public class AnalyticsDAO {

    // 1. Player skill progression & ranking over time
    public void getPlayerSkillProgression(String playerId) {
        String sql = """
            SELECT P.username, G.name AS game_name, PP.game_currency AS current_score
            FROM PlayerProfile PP
            JOIN Player P ON PP.player_id = P.player_id
            JOIN Game G ON PP.game_id = G.game_id
            WHERE P.player_id = ?
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playerId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("=== Player Skill Progression ===");
            while (rs.next()) {
                System.out.println(rs.getString("username") + " | " +
                        rs.getString("game_name") + " | " +
                        rs.getDouble("current_score"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 2. Most popular games & match types by region
    public void getPopularGamesByRegion() {
        String sql = """
            SELECT P.region, G.name AS game_name, COUNT(PP.profile_id) AS players
            FROM PlayerProfile PP
            JOIN Player P ON PP.player_id = P.player_id
            JOIN Game G ON PP.game_id = G.game_id
            GROUP BY P.region, G.name
            ORDER BY players DESC
        """;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("=== Popular Games by Region ===");
            while (rs.next()) {
                System.out.println(rs.getString("region") + " | " +
                        rs.getString("game_name") + " | " +
                        rs.getInt("players"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 3. Tournament participation & prize pool distribution
    public void getTournamentParticipation() {
        String sql = """
            SELECT T.name, COUNT(TR.registration_id) AS participants, T.prize_pool
            FROM Tournament T
            LEFT JOIN TournamentRegistration TR ON T.tournament_id = TR.tournament_id
            GROUP BY T.name, T.prize_pool
            ORDER BY participants DESC
        """;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("=== Tournament Participation & Prize Pools ===");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " | Participants: " +
                        rs.getInt("participants") + " | Prize Pool: $" +
                        rs.getDouble("prize_pool"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 4. Virtual economy transaction volumes
    public void getVirtualEconomyStats() {
        String sql = """
            SELECT VI.name AS item_name, SUM(IT.quantity) AS total_traded, SUM(IT.value) AS total_value
            FROM ItemTransaction IT
            JOIN VirtualItem VI ON IT.item_id = VI.item_id
            GROUP BY VI.name
            ORDER BY total_value DESC
        """;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("=== Virtual Economy Stats ===");
            while (rs.next()) {
                System.out.println(rs.getString("item_name") + " | Traded: " +
                        rs.getInt("total_traded") + " | Value: $" +
                        rs.getDouble("total_value"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 5. Cheating detection reports
    public void getCheatingReports() {
        String sql = """
            SELECT CR.report_id, P1.username AS reported_user, P2.username AS reporter, CR.description, CR.status
            FROM CheatingReport CR
            JOIN PlayerProfile PR1 ON CR.reported_profile_id = PR1.profile_id
            JOIN Player P1 ON PR1.player_id = P1.player_id
            JOIN PlayerProfile PR2 ON CR.reporter_profile_id = PR2.profile_id
            JOIN Player P2 ON PR2.player_id = P2.player_id
        """;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("=== Cheating Reports ===");
            while (rs.next()) {
                System.out.println("Report #" + rs.getString("report_id") +
                        " | Reported: " + rs.getString("reported_user") +
                        " | Reporter: " + rs.getString("reporter") +
                        " | Status: " + rs.getString("status") +
                        " | Desc: " + rs.getString("description"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 6. Revenue by source
    public void getRevenueStats() {
        String sql = """
            SELECT source, SUM(amount) AS total_revenue
            FROM Revenue
            GROUP BY source
            ORDER BY total_revenue DESC
        """;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("=== Revenue by Source ===");
            while (rs.next()) {
                System.out.println(rs.getString("source") + " : $" +
                        rs.getDouble("total_revenue"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
