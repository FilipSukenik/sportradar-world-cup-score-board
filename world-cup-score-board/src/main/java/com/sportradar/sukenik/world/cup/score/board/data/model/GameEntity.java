package com.sportradar.sukenik.world.cup.score.board.data.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;

public class GameEntity {

    private Integer gameId;

    private TeamEntity homeTeam;

    private TeamEntity awayTeam;

    /**
     * Default constructor so {@link GameEntity} is Java Bean.
     */
    public GameEntity() {

    }

    public GameEntity(@Nullable Integer gameId, @NotNull String homeTeamName, @NotNull String awayTeamName) {

        this.gameId = gameId;
        homeTeam = new TeamEntity(homeTeamName);
        awayTeam = new TeamEntity(awayTeamName);
    }

    /**
     * Creates deep copy of provided instance.
     * @param toCopy - not null source of data.
     */
    public GameEntity(GameEntity toCopy) {

        Assert.notNull(toCopy, "Cannot create copy of null GameEntity");

        this.gameId = toCopy.getGameId();
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

    public void setHomeTeam(TeamEntity homeTeam) {

        this.homeTeam = homeTeam;
    }

    public TeamEntity getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(TeamEntity awayTeam) {

        this.awayTeam = awayTeam;
    }

    public int getTotalScore() {

        return extractScore(homeTeam) + extractScore(awayTeam);
    }

    private int extractScore(@Nullable TeamEntity team) {

        return null == team ? 0 : team.getScore();
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {

        updateScore(homeTeamScore, homeTeam);
        updateScore(awayTeamScore, awayTeam);
    }

    public void updateScore(int score, TeamEntity team) {

        if (null == team) {
            return;
        }
        team.setScore(score);
    }

    @Override
    public String toString() {

        return "GameEntity{" +
                "gameId=" + gameId +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }
}
