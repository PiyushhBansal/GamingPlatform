package dao;

import db.DBConnection;
import java.sql.*;
import java.util.UUID;

public class TeamDAO {
    public void createTeam(String teamName, String creatorId) {
        String sql = "INSERT INTO Team (team_id, team_name, created_by, creation_date) VALUES (?, ?, ?, CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, teamName);
            stmt.setString(3, creatorId);
            stmt.executeUpdate();
            System.out.println("Team created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
