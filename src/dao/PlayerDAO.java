package dao;

import db.DBConnection;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerDAO {

    public void createPlayer(Player player) {
        String sql = "INSERT INTO Player (player_id, username, email, region, join_date) VALUES (?, ?, ?, ?, CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, player.getUsername());
            stmt.setString(3, player.getEmail());
            stmt.setString(4, player.getRegion());
            stmt.executeUpdate();

            System.out.println("Player registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Player";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Player player = new Player(
                    rs.getString("player_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("region"),
                    rs.getDate("join_date")
                );
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
