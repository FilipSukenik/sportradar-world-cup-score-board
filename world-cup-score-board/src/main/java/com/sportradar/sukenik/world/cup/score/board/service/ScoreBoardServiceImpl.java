/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.service;

import java.util.List;
import java.util.Optional;

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
    public GameDto startGame(String homeTeamName, String awayTeamName) {

        GameEntity gameEntity = new GameEntity(null, homeTeamName, awayTeamName);
        gameEntity = scoreBoardDao.save(gameEntity);
        return gameMapper.mapEntityToDto(gameEntity);
    }

    @Override
    public void finishGameById(int gameId) {

        Optional<GameEntity> entityOptional = scoreBoardDao.findGameById(gameId);

        if (entityOptional.isEmpty()) {
            // TODO throw not found exception
            return;
        }
        scoreBoardDao.removeGame(entityOptional.get());
    }

    @Override
    public void finishGame(GameDto gameDto) {

        finishGameById(gameDto.getGameId());
    }

    @Override
    public void updateGameScore(int gameId, int newHomeTeamScore, int newAwayTeamScore) {

        Optional<GameEntity> entityOptional = scoreBoardDao.findGameById(gameId);

        if (entityOptional.isEmpty()) {
            // TODO throw not found exception
            return;
        }
        GameEntity dbEntity = entityOptional.get();
        dbEntity.getHomeTeam().setScore(newHomeTeamScore);
        dbEntity.getAwayTeam().setScore(newAwayTeamScore);
        //TODO store change
    }

    @Override
    public List<GameDto> getSummary() {

        List<GameEntity> dbRecords = scoreBoardDao.getGameDbList();
        dbRecords.sort(new TotalScoreDescendingTimestampAscendingGameComparator());
        return gameMapper.mapAllEntityToDto(dbRecords);
    }
}
