/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.service;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.comaparator.TotalScoreDescendingTimestampAscendingGameComparator;
import com.sportradar.sukenik.world.cup.score.board.data.ScoreBoardDao;
import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.mapper.impl.GameEntityDtoMapper;

public class ScoreBoardServiceImpl implements ScoreBoardService {

    private final ScoreBoardDao scoreBoardDao;

    private final GameEntityDtoMapper gameMapper;

    public ScoreBoardServiceImpl(ScoreBoardDao scoreBoardDao,
            GameEntityDtoMapper gameMapper) {

        this.scoreBoardDao = scoreBoardDao;
        this.gameMapper = gameMapper;
    }

    @Override
    @Nullable
    public GameDto startGame(String homeTeamName, String awayTeamName) {

        Assert.hasText(homeTeamName, "homeTeamName cannot be nul nor empty string");
        Assert.hasText(awayTeamName, "awayTeamName cannot be nul nor empty string");

        GameEntity gameEntity = new GameEntity(null, homeTeamName, awayTeamName);
        gameEntity = scoreBoardDao.save(gameEntity);
        return gameMapper.mapEntityToDto(gameEntity);
    }

    @Override
    public boolean finishGameById(Integer gameId) {

        Optional<GameEntity> entityOptional = scoreBoardDao.findGameById(gameId);

        if (entityOptional.isEmpty()) {

            return false;
        }
        return scoreBoardDao.removeGame(entityOptional.get());
    }

    @Override
    public boolean finishGame(@NotNull GameDto gameDto) {

        Assert.notNull(gameDto, "gameDto cannot be null");

        return finishGameById(gameDto.getGameId());
    }

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

    @Override
    @NotNull
    public List<GameDto> getSummary() {

        List<GameEntity> dbRecords = scoreBoardDao.getGameDbList();
        dbRecords.sort(new TotalScoreDescendingTimestampAscendingGameComparator());
        return gameMapper.mapAllEntitiesToDtos(dbRecords);
    }
}
