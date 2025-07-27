CREATE DATABASE IF NOT EXISTS gaming_platform;
USE gaming_platform;

-- -----------------------------
-- GenreType, PlanType, ActionType, TransactionType
-- -----------------------------
CREATE TABLE GenreType (
    genre_id CHAR(36) PRIMARY KEY,
    genre_name VARCHAR(50)
);

CREATE TABLE PlanType (
    plan_type_id CHAR(36) PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    benefits TEXT
);

CREATE TABLE ActionType (
    action_type_id CHAR(36) PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    description TEXT
);

CREATE TABLE TransactionType (
    transaction_type_id CHAR(36) PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    description TEXT
);

-- -----------------------------
-- Player & Game
-- -----------------------------
CREATE TABLE Player (
    player_id CHAR(36) PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    region VARCHAR(50),
    join_date DATE
);

CREATE TABLE Game (
    game_id CHAR(36) PRIMARY KEY,
    name VARCHAR(100),
    genre CHAR(36),
    release_date DATE,
    FOREIGN KEY (genre) REFERENCES GenreType(genre_id)
);

-- -----------------------------
-- Player Profile
-- -----------------------------
CREATE TABLE PlayerProfile (
    profile_id CHAR(36) PRIMARY KEY,
    player_id CHAR(36),
    game_id CHAR(36),
    preferences TEXT,
    avatar_url VARCHAR(255),
    game_currency DECIMAL(10,2),
    FOREIGN KEY (player_id) REFERENCES Player(player_id),
    FOREIGN KEY (game_id) REFERENCES Game(game_id),
    INDEX (player_id)
);

-- -----------------------------
-- Tournament & Registration
-- -----------------------------
CREATE TABLE Team (
    team_id CHAR(36) PRIMARY KEY,
    team_name VARCHAR(100),
    created_by CHAR(36),
    creation_date DATE,
    FOREIGN KEY (created_by) REFERENCES Player(player_id)
);

CREATE TABLE Tournament (
    tournament_id CHAR(36) PRIMARY KEY,
    game_id CHAR(36),
    name VARCHAR(100),
    start_date DATE,
    end_date DATE,
    prize_pool DECIMAL(10,2),
    FOREIGN KEY (game_id) REFERENCES Game(game_id)
);

CREATE TABLE TournamentRegistration (
    registration_id CHAR(36) PRIMARY KEY,
    tournament_id CHAR(36),
    team_id CHAR(36),
    FOREIGN KEY (tournament_id) REFERENCES Tournament(tournament_id),
    FOREIGN KEY (team_id) REFERENCES Team(team_id),
    INDEX (tournament_id),
    INDEX (team_id)
);

-- -----------------------------
-- Virtual Items & Transactions
-- -----------------------------
CREATE TABLE VirtualItem (
    item_id CHAR(36) PRIMARY KEY,
    game_id CHAR(36),
    name VARCHAR(100),
    base_value DECIMAL(10,2),
    FOREIGN KEY (game_id) REFERENCES Game(game_id)
);

CREATE TABLE ItemTransaction (
    transaction_id CHAR(36) PRIMARY KEY,
    sender_id CHAR(36),
    receiver_id CHAR(36),
    item_id CHAR(36),
    quantity INT,
    transaction_type CHAR(36),
    timestamp TIMESTAMP,
    value DECIMAL(10,2),
    FOREIGN KEY (sender_id) REFERENCES Player(player_id),
    FOREIGN KEY (receiver_id) REFERENCES Player(player_id),
    FOREIGN KEY (item_id) REFERENCES VirtualItem(item_id),
    FOREIGN KEY (transaction_type) REFERENCES TransactionType(transaction_type_id),
    INDEX (sender_id),
    INDEX (receiver_id),
    INDEX (item_id)
);

-- -----------------------------
-- Cheating & Moderation
-- -----------------------------
CREATE TABLE CheatingReport (
    report_id CHAR(36) PRIMARY KEY,
    reported_profile_id CHAR(36),
    reporter_profile_id CHAR(36),
    description TEXT,
    date_reported DATE,
    status VARCHAR(50),
    FOREIGN KEY (reported_profile_id) REFERENCES PlayerProfile(profile_id),
    FOREIGN KEY (reporter_profile_id) REFERENCES PlayerProfile(profile_id)
);

CREATE TABLE Moderator (
    moderator_id CHAR(36) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    role VARCHAR(50),
    join_date DATE
);

CREATE TABLE ModerationAction (
    action_id CHAR(36) PRIMARY KEY,
    report_id CHAR(36),
    moderator_id CHAR(36),
    action_type_id CHAR(36),
    reason TEXT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    FOREIGN KEY (report_id) REFERENCES CheatingReport(report_id),
    FOREIGN KEY (moderator_id) REFERENCES Moderator(moderator_id),
    FOREIGN KEY (action_type_id) REFERENCES ActionType(action_type_id)
);

-- -----------------------------
-- Revenue
-- -----------------------------
CREATE TABLE Revenue (
    revenue_id CHAR(36) PRIMARY KEY,
    player_id CHAR(36),
    game_id CHAR(36),
    source VARCHAR(50), -- 'subscription', 'item_purchase', etc.
    amount DECIMAL(10,2),
    date DATE,
    FOREIGN KEY (player_id) REFERENCES Player(player_id),
    FOREIGN KEY (game_id) REFERENCES Game(game_id)
);
