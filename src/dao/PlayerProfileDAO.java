package dao;

import db.DBConnection;
import java.sql.*;
import java.util.UUID;

public class PlayerProfileDAO {
    public void createProfile(String playerId, String gameId, String preferences, String avatarUrl, double currency) {
        String sql = "INSERT INTO PlayerProfile (profile_id, player_id, game_id, preferences, avatar_url, game_currency) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, playerId);
            stmt.setString(3, gameId);
            stmt.setString(4, preferences);
            stmt.setString(5, avatarUrl);
            stmt.setDouble(6, currency);
            stmt.executeUpdate();

            System.out.println("Profile created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
