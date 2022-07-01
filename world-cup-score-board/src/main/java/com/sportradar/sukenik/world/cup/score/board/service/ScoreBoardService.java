/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.service;

import java.util.List;

import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;

public interface ScoreBoardService {

    GameDto startGame(String homeTeamName, String awayTeamName);

    boolean finishGameById(Integer gameId);

    boolean finishGame(GameDto gameDto);

    boolean updateGameScore(Integer gameId, int newHomeTeamScore, int newAwayTeamScore);

    List<GameDto> getSummary();
}
