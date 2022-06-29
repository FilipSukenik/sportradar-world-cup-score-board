/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.data.model;

import java.util.Date;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;

public class GameEntity {

    private Integer gameId;

    private TeamEntity homeTeam;

    private TeamEntity awayTeam;

    private Date creationTimestamp;

    /**
     * Default constructor so {@link GameEntity} is Java Bean.
     */
    public GameEntity() {

        creationTimestamp = new Date();
    }

    public GameEntity(@Nullable Integer gameId, @NotNull String homeTeamName, @NotNull String awayTeamName) {

        this.gameId = gameId;
        homeTeam = new TeamEntity(homeTeamName);
        awayTeam = new TeamEntity(awayTeamName);
        creationTimestamp = new Date();
    }

    /**
     * Creates deep copy of provided instance.
     * @param toCopy - not null source of data.
     */
    public GameEntity(GameEntity toCopy) {

        Assert.notNull(toCopy, "Cannot create copy of null GameEntity");

        this.gameId = toCopy.getGameId();
        this.creationTimestamp = toCopy.getCreationTimestamp();
        this.homeTeam = new TeamEntity(toCopy.getHomeTeam());
        this.awayTeam = new TeamEntity(toCopy.getAwayTeam());
    }

    public Integer getGameId() {

        return gameId;
    }

    public void setGameId(Integer gameId) {

        this.gameId = gameId;
    }

    public TeamEntity getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(@NotNull TeamEntity homeTeam) {

        Assert.notNull(homeTeam, "homeEntity cannot be null");

        this.homeTeam = homeTeam;
    }

    public TeamEntity getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(@NotNull TeamEntity awayTeam) {

        Assert.notNull(awayTeam, "awayTeam cannot be null");

        this.awayTeam = awayTeam;
    }

    public Date getCreationTimestamp() {

        return creationTimestamp;
    }

    public void setCreationTimestamp(@NotNull Date creationTimestamp) {

        Assert.notNull(creationTimestamp, "creationTimestamp cannot be null");

        this.creationTimestamp = creationTimestamp;
    }

    public int getTotalScore() {

        return extractScore(homeTeam) + extractScore(awayTeam);
    }

    private int extractScore(@Nullable TeamEntity team) {

        return null == team ? 0 : team.getScore();
    }
}
