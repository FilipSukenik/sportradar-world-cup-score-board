/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board;

import java.util.List;

import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DefaultDependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.exception.TechnicalException;

public class ScoreBoardImpl implements ScoreBoard {

    private final ScoreBoard scoreBoardService;

    private ScoreBoardImpl() {

        DependencyFactory dependencyFactory = new DefaultDependencyFactory();
        dependencyFactory.init();
        scoreBoardService = dependencyFactory.getDependency(ScoreBoard.class);
    }

    public ScoreBoardImpl(DependencyFactory dependencyFactory) {

        dependencyFactory.init();
        scoreBoardService = dependencyFactory.getDependency(ScoreBoard.class);
    }

    @Override
    public GameDto startGame(String homeTeamName, String awayTeamName) {

        try {
            return scoreBoardService.startGame(homeTeamName, awayTeamName);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new TechnicalException("Exception wast thrown during startGame() execution", e);
        }
    }

    @Override
    public boolean finishGameById(Integer gameId) {

        try {
            return scoreBoardService.finishGameById(gameId);
        } catch (Exception e) {
            throw new TechnicalException("Exception wast thrown during finishGameById() execution", e);
        }
    }

    @Override
    public boolean finishGame(GameDto gameDto) {

        try {
            return scoreBoardService.finishGame(gameDto);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new TechnicalException("Exception wast thrown during finishGame() execution", e);
        }
    }

    @Override
    public boolean updateGameScore(Integer gameId, int newHomeTeamScore, int newAwayTeamScore) {

        try {
            return scoreBoardService.updateGameScore(gameId, newHomeTeamScore, newAwayTeamScore);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new TechnicalException("Exception wast thrown during updateGameScore() execution", e);
        }
    }

    @Override
    public List<GameDto> getSummary() {

        try {
            return scoreBoardService.getSummary();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new TechnicalException("Exception wast thrown during getSummary() execution", e);
        }
    }
}
