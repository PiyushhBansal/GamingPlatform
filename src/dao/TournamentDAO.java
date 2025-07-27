package dao;
import db.DBConnection;
import java.sql.*;
import java.util.UUID;

public class TournamentDAO {
    public void registerTeamToTournament(String tournamentId, String teamId) {
        String sql = "INSERT INTO TournamentRegistration (registration_id, tournament_id, team_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, tournamentId);
            stmt.setString(3, teamId);

            stmt.executeUpdate();
            System.out.println("Team registered to tournament");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
