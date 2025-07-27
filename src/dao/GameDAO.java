package dao;

import db.DBConnection;
import model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameDAO {

    public void addGame(Game game) {
        String query = "INSERT INTO Game (game_id, name, genre, release_date) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, game.getName());
            ps.setString(3, game.getGenre());
            ps.setDate(4, new java.sql.Date(game.getReleaseDate().getTime()));

            ps.executeUpdate();
            System.out.println("Game added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM Game";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Game game = new Game(
                    rs.getString("game_id"),
                    rs.getString("name"),
                    rs.getString("genre"),
                    rs.getDate("release_date")
                );
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public void deleteGame(String id) {
        String query = "DELETE FROM Game WHERE game_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Game deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGame(Game game) {
        String query = "UPDATE Game SET name=?, genre=?, release_date=? WHERE game_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, game.getName());
            ps.setString(2, game.getGenre());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setString(4, game.getGameId());
            ps.executeUpdate();
            System.out.println("Game updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
