package dao;

import db.DBConnection;
import java.sql.*;
import java.util.UUID;

public class RevenueDAO {
    public void logRevenue(String playerId, String gameId, String source, double amount) {
        String sql = "INSERT INTO Revenue (revenue_id, player_id, game_id, source, amount, date) VALUES (?, ?, ?, ?, ?, CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, playerId);
            stmt.setString(3, gameId);
            stmt.setString(4, source);
            stmt.setDouble(5, amount);
            stmt.executeUpdate();
            System.out.println("Revenue logged");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
