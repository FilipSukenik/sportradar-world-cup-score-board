/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.dto;

import java.util.Date;

public class GameDto {

    private int gameId;

    private TeamDto homeTeam;

    private TeamDto awayTeam;

    private Date creationTimestamp;

    /**
     * Default constructor so {@link GameDto} is Java Bean.
     */
    public GameDto() {

    }

    public int getGameId() {

        return gameId;
    }

    public void setGameId(int gameId) {

        this.gameId = gameId;
    }

    public TeamDto getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(TeamDto homeTeam) {

        this.homeTeam = homeTeam;
    }

    public TeamDto getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(TeamDto awayTeam) {

        this.awayTeam = awayTeam;
    }

    public Date getCreationTimestamp() {

        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {

        this.creationTimestamp = creationTimestamp;
    }
}
