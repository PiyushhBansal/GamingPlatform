package model;

import java.util.Date;

public class Game {
    private String gameId;
    private String name;
    private String genre;
    private Date releaseDate;

    public Game() {}

    public Game(String gameId, String name, String genre, Date releaseDate) {
        this.gameId = gameId;
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Game(String name, String genre, Date releaseDate) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    // Getters & Setters
    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
}
