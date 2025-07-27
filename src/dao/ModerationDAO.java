package dao;

import db.DBConnection;
import java.sql.*;
import java.util.UUID;

public class ModerationDAO {
    public void reportCheating(String reportedId, String reporterId, String description) {
        String sql = "INSERT INTO CheatingReport (report_id, reported_profile_id, reporter_profile_id, description, date_reported, status) VALUES (?, ?, ?, ?, CURDATE(), 'pending')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, reportedId);
            stmt.setString(3, reporterId);
            stmt.setString(4, description);
            stmt.executeUpdate();
            System.out.println("Cheating report submitted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
