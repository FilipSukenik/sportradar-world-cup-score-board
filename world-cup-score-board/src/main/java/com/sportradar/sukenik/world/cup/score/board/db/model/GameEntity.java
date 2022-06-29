/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.db.model;

import java.util.Date;

public class GameEntity {

    private int gameId;

    private TeamEntity homeTeam;

    private TeamEntity awayTeam;

    private Date creationTimestamp;

    /**
     * Default constructor so {@link GameEntity} is Java Bean.
     */
    public GameEntity() {

        creationTimestamp = new Date();
    }

    public GameEntity(int gameId, String homeTeamName, String awayTeamName) {

        this.gameId = gameId;
        homeTeam = new TeamEntity(homeTeamName);
        awayTeam = new TeamEntity(awayTeamName);
        creationTimestamp = new Date();
    }

    public int getGameId() {

        return gameId;
    }

    public void setGameId(int gameId) {

        this.gameId = gameId;
    }

    public TeamEntity getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(TeamEntity homeTeam) {

        this.homeTeam = homeTeam;
    }

    public TeamEntity getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(TeamEntity awayTeam) {

        this.awayTeam = awayTeam;
    }

    public Date getCreationTimestamp() {

        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {

        this.creationTimestamp = creationTimestamp;
    }

    public int getTotalScore() {

        return homeTeam.getScore() + awayTeam.getScore();
    }
}
