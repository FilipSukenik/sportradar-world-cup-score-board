
package com.sportradar.sukenik.world.cup.score.board;

import java.util.List;

import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;

public interface ScoreBoard {

    GameDto startGame(String homeTeamName, String awayTeamName);

    boolean finishGameById(Integer gameId);

    boolean finishGame(GameDto gameDto);

    boolean updateGameScore(Integer gameId, int newHomeTeamScore, int newAwayTeamScore);

    List<GameDto> getSummary();
}
