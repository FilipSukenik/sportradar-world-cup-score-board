package com.sportradar.sukenik.world.cup.score.board.service;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.ScoreBoard;
import com.sportradar.sukenik.world.cup.score.board.comaparator.TotalScoreDescendingTimestampAscendingGameComparator;
import com.sportradar.sukenik.world.cup.score.board.data.ScoreBoardDao;
import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.mapper.impl.GameEntityDtoMapper;

/**
 * Service that implements all business logic needed in our library.
 */
public class ScoreBoardServiceImpl implements ScoreBoard {

    private final ScoreBoardDao scoreBoardDao;

    private final GameEntityDtoMapper gameMapper;

    public ScoreBoardServiceImpl(ScoreBoardDao scoreBoardDao,
            GameEntityDtoMapper gameMapper) {

        this.scoreBoardDao = scoreBoardDao;
        this.gameMapper = gameMapper;
    }

    /**
     * Creates instance of {@link GameEntity} from name of home and away teams.
     * @param homeTeamName name of home team.
     * @param awayTeamName name of away team.
     * @return copy of data stored that were stored in database.
     */
    @Override
    @Nullable
    public GameDto startGame(String homeTeamName, String awayTeamName) {

        Assert.hasText(homeTeamName, "homeTeamName cannot be nul nor empty string");
        Assert.hasText(awayTeamName, "awayTeamName cannot be nul nor empty string");

        GameEntity gameEntity = new GameEntity(null, homeTeamName, awayTeamName);
        gameEntity = scoreBoardDao.save(gameEntity);
        return gameMapper.mapEntityToDto(gameEntity);
    }

    /**
     * Searches database for entity by gameId. In case that game was not found returns false. In case that game was found
     * removed game from database.
     * @param gameId id of game that should be finished.
     * @return {@code true} when game was removed successfully else returns {@code false}.
     */
    @Override
    public boolean finishGameById(Integer gameId) {

        Optional<GameEntity> entityOptional = scoreBoardDao.findGameById(gameId);

        if (entityOptional.isEmpty()) {

            return false;
        }
        return scoreBoardDao.removeGame(entityOptional.get());
    }

    /**
     * Overloaded method that allows client to finish game providing {@link GameDto} instead of gameId.
     * @param gameDto game that should be removed
     * @return {@code true} when game was removed successfully else returns {@code false}.
     */
    @Override
    public boolean finishGame(@NotNull GameDto gameDto) {

        Assert.notNull(gameDto, "gameDto cannot be null");

        return finishGameById(gameDto.getGameId());
    }

    /**
     * Searches database by gameId. If {@link GameEntity} was not found returns false. In case that {@link GameEntity} was
     * found in database updates score and persists changes. After everything is done returns {@code true}.
     * @param gameId id of game that the score should be updated.
     * @param newHomeTeamScore new value for home team.
     * @param newAwayTeamScore new value for away team.
     * @return {@code true} if score was updated successfully els returns {@code false}.
     */
    @Override
    public boolean updateGameScore(Integer gameId, int newHomeTeamScore, int newAwayTeamScore) {

        Optional<GameEntity> entityOptional = scoreBoardDao.findGameById(gameId);

        if (entityOptional.isEmpty()) {

            return false;
        }
        GameEntity dbEntity = entityOptional.get();
        dbEntity.updateScore(newHomeTeamScore, newAwayTeamScore);
        scoreBoardDao.save(dbEntity);
        return true;
    }

    /**
     * Returns summary of actual score board. Uses {@link TotalScoreDescendingTimestampAscendingGameComparator} to
     * order elements in proper order
     * @return dtos of all entities in correct order.
     */
    @Override
    @NotNull
    public List<GameDto> getSummary() {

        List<GameEntity> dbRecords = scoreBoardDao.getGameDbList();
        dbRecords.sort(new TotalScoreDescendingTimestampAscendingGameComparator());
        return gameMapper.mapAllEntitiesToDtos(dbRecords);
    }
}
