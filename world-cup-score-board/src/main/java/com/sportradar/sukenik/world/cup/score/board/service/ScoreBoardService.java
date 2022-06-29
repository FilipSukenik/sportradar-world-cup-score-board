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

    void finishGameById(int gameId);

    void finishGame(GameDto gameDto);

    void updateGameScore(int gameId, int newHomeTeamScore, int newAwayTeamScore);

    List<GameDto> getSummary();
}
