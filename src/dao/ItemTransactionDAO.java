package dao;

import db.DBConnection;
import java.sql.*;
import java.util.UUID;

public class ItemTransactionDAO {
    public void recordTransaction(String senderId, String receiverId, String itemId, int quantity, int typeId, double value) {
        String sql = "INSERT INTO ItemTransaction (transaction_id, sender_id, receiver_id, item_id, quantity, transaction_type, value, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, senderId);
            stmt.setString(3, receiverId);
            stmt.setString(4, itemId);
            stmt.setInt(5, quantity);
            stmt.setInt(6, typeId);
            stmt.setDouble(7, value);

            stmt.executeUpdate();
            System.out.println("✅ Item transaction recorded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
