package com.sportradar.sukenik.world.cup.score.board;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {

        scoreBoard = new ScoreBoardImpl();
    }

    @Test
    void integrationRequirementTest() {

        GameDto game = scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateGameScore(game.getGameId(), 0, 5);

        game = scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateGameScore(game.getGameId(), 10, 2);

        game = scoreBoard.startGame("Germany", "France");
        scoreBoard.updateGameScore(game.getGameId(), 2, 2);

        game = scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateGameScore(game.getGameId(), 6, 6);

        game = scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateGameScore(game.getGameId(), 3, 1);

        List<GameDto> summary = scoreBoard.getSummary();

        game = summary.get(0);
        Assertions.assertEquals("Uruguay", game.getHomeTeam().getName());
        Assertions.assertEquals(6, game.getHomeTeam().getScore());
        Assertions.assertEquals("Italy", game.getAwayTeam().getName());
        Assertions.assertEquals(6, game.getAwayTeam().getScore());

        game = summary.get(1);
        Assertions.assertEquals("Spain", game.getHomeTeam().getName());
        Assertions.assertEquals(10, game.getHomeTeam().getScore());
        Assertions.assertEquals("Brazil", game.getAwayTeam().getName());
        Assertions.assertEquals(2, game.getAwayTeam().getScore());

        game = summary.get(2);
        Assertions.assertEquals("Mexico", game.getHomeTeam().getName());
        Assertions.assertEquals(0, game.getHomeTeam().getScore());
        Assertions.assertEquals("Canada", game.getAwayTeam().getName());
        Assertions.assertEquals(5, game.getAwayTeam().getScore());

        game = summary.get(3);
        Assertions.assertEquals("Argentina", game.getHomeTeam().getName());
        Assertions.assertEquals(3, game.getHomeTeam().getScore());
        Assertions.assertEquals("Australia", game.getAwayTeam().getName());
        Assertions.assertEquals(1, game.getAwayTeam().getScore());

        game = summary.get(4);
        Assertions.assertEquals("Germany", game.getHomeTeam().getName());
        Assertions.assertEquals(2, game.getHomeTeam().getScore());
        Assertions.assertEquals("France", game.getAwayTeam().getName());
        Assertions.assertEquals(2, game.getAwayTeam().getScore());

        // test finishing of the game
        scoreBoard.finishGameById(4);

        summary = scoreBoard.getSummary();

        Assertions.assertEquals(4, summary.size());

        game = summary.get(0);
        Assertions.assertEquals("Spain", game.getHomeTeam().getName());
        Assertions.assertEquals(10, game.getHomeTeam().getScore());
        Assertions.assertEquals("Brazil", game.getAwayTeam().getName());
        Assertions.assertEquals(2, game.getAwayTeam().getScore());
    }
}