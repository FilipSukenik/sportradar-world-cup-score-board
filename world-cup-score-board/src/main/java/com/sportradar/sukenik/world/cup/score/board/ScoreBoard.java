
package com.sportradar.sukenik.world.cup.score.board;

import java.util.List;

import com.sportradar.sukenik.world.cup.score.board.comaparator.TotalScoreDescendingTimestampAscendingGameComparator;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;

/**
 * Interface for score board defines all methods that the score board should implement.
 */
public interface ScoreBoard {

    /**
     * Creates new game from home team and away team names.
     * @param homeTeamName name of home team.
     * @param awayTeamName name of away team.
     * @return data that was stored in database.
     */
    GameDto startGame(String homeTeamName, String awayTeamName);

    /**
     * Based on gameId finds the game and finishes it (removes it from score board)
     * @param gameId id of game that should be removed.
     * @return {@code true} in case that game was finished else returns {@code false}.
     */
    boolean finishGameById(Integer gameId);

    /**
     * Overloaded method that allows client to finish game providing {@link GameDto} instead of gameId.
     * @param gameDto game that should be finished.
     * @return {@code true} in case that game was finished else returns {@code false}.
     */
    boolean finishGame(GameDto gameDto);

    /**
     * Updates score of game.
     * @param gameId id of game that the score should be updated
     * @param newHomeTeamScore new value for score of home team.
     * @param newAwayTeamScore new value for score of away team.
     * @return {@code true} in case that game score was updated successfully else returns {@code false}.
     */
    boolean updateGameScore(Integer gameId, int newHomeTeamScore, int newAwayTeamScore);

    /**
     * Returns summary of actual score board. Uses {@link TotalScoreDescendingTimestampAscendingGameComparator} to
     * order elements in proper order
     * @return dtos of all entities in correct order.
     */
    List<GameDto> getSummary();
}
