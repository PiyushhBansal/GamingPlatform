package model;

import java.util.Date;

public class Player {
    private String playerId;
    private String username;
    private String email;
    private String region;
    private Date joinDate;

    public Player() {}

    public Player(String playerId, String username, String email, String region, Date joinDate) {
        this.playerId = playerId;
        this.username = username;
        this.email = email;
        this.region = region;
        this.joinDate = joinDate;
    }

    public Player(String username, String email, String region) {
        this.username = username;
        this.email = email;
        this.region = region;
    }

    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
}
