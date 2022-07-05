/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.data;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;

public interface ScoreBoardDao {

    @NotNull
    List<GameEntity> getGameDbList();

    @NotNull
    GameEntity save(@NotNull GameEntity toStoreEntity);

    Optional<GameEntity> findGameById(Integer gameId);

    boolean removeGame(@NotNull GameEntity toRemoveEntity);
}
