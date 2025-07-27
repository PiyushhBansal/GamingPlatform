package dao;

import db.DBConnection;
import java.sql.*;

public class TransactionDAO {

    // Fetch transactions for a player
    public void getPlayerTransactions(String playerId) {
        String sql = """
            SELECT IT.timestamp, VI.name, IT.quantity, IT.value
            FROM ItemTransaction IT
            JOIN VirtualItem VI ON IT.item_id = VI.item_id
            WHERE IT.sender_id = ? OR IT.receiver_id = ?
            ORDER BY IT.timestamp DESC
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playerId);
            stmt.setString(2, playerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getTimestamp("timestamp") + " | " +
                        rs.getString("name") + " | Qty: " +
                        rs.getInt("quantity") + " | Value: " +
                        rs.getDouble("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Record a new transaction
   public void recordTransaction(String senderId, String receiverId, int itemId, int quantity, int typeId, double value){
        String sql = """
            INSERT INTO ItemTransaction (sender_id, receiver_id, item_id, quantity, type, value, timestamp)
            VALUES (?, ?, ?, ?, ?, ?, NOW())
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, senderId);
            stmt.setString(2, receiverId);
            stmt.setInt(3, itemId);
            stmt.setInt(4, quantity);
            stmt.setInt(5, typeId);
            stmt.setDouble(6, value);
            stmt.executeUpdate();
            System.out.println("Transaction recorded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
