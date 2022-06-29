/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sportradar.sukenik.world.cup.score.board.db.model.GameEntity;

public class ScoreBoardDao {

    private final List<GameEntity> gameDbList = new ArrayList<>();

    public List<GameEntity> getGameDbList() {

        return new ArrayList<>(gameDbList);
    }

    public void addGame(GameEntity gameEntity) {

        gameDbList.add(gameEntity);
    }

    public Optional<GameEntity> findGameById(int gameId) {

        return gameDbList.stream()
                .filter(gameEntity -> gameId == gameEntity.getGameId()).findFirst();
    }

    public void removeGame(GameEntity toRemoveEntity) {

        gameDbList.removeIf(dbEntity -> toRemoveEntity.getGameId() == dbEntity.getGameId());
    }
}
