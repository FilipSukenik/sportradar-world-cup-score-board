/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.dto;

public class GameDto {

    private Integer gameId;

    private TeamDto homeTeam;

    private TeamDto awayTeam;

    /**
     * Default constructor so {@link GameDto} is Java Bean.
     */
    public GameDto() {

    }

    public GameDto(Integer gameId) {

        this.gameId = gameId;
    }

    public GameDto(String homeTeamName, String awayTeamName) {

        gameId = 0;
        this.homeTeam = new TeamDto(homeTeamName);
        this.awayTeam = new TeamDto(awayTeamName);
    }

    public Integer getGameId() {

        return gameId;
    }

    public void setGameId(Integer gameId) {

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

    @Override
    public String toString() {

        return "GameDto{" +
                "gameId=" + gameId +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }
}
