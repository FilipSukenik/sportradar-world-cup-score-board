/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;

/**
 * Data component that stores in data in memory. Contains list of all games on score board. List is final field so cannot be changed
 * from outside.
 */
public class ScoreBoardDao {

    private final List<GameEntity> gameDbList;

    private final AtomicInteger idGenerator;

    public ScoreBoardDao(AtomicInteger idGenerator) {

        this.idGenerator = idGenerator;
        this.gameDbList = new ArrayList<>();
    }

    /**
     * Returns copy of the list, however, all objects are deep copies, so alteration of object from calling code does not
     * change value in database.
     * @return copy of {@link ArrayList} filled with copies of {@link GameEntity}.
     */
    public List<GameEntity> getGameDbList() {

        return gameDbList.stream()
                .map(GameEntity::new)
                .collect(Collectors.toList());
    }

    /**
     * Save game into database. In case that {@code toStoreEntity} method {@link GameEntity#getGameId()} returns null
     * the entity receives id from {@link ScoreBoardDao#idGenerator} and entity is stored in database. In case that
     * {@code toStoreEntity} already has id, first the database is searched whether it already contains entity with same {@link GameEntity#getGameId()}.
     * If no entity is found we add {@code toStoreEntity} into database, else we update values of existing entity.
     * @param toStoreEntity entity that wants to be stored to database
     */
    public GameEntity save(@NotNull GameEntity toStoreEntity) {

        Assert.notNull(toStoreEntity, "cannot save null into database");

        // make copy so the client storing object cannot update value in database after storing
        GameEntity copiedEntity = new GameEntity(toStoreEntity);

        if (null == copiedEntity.getGameId()) {
            copiedEntity.setGameId(idGenerator.getAndIncrement());
            gameDbList.add(copiedEntity);
            return new GameEntity(copiedEntity);
        }
        Optional<GameEntity> dbEntityOptional = gameDbList.stream()
                .filter(dbEntity -> dbEntity.getGameId().equals(copiedEntity.getGameId())).findFirst();

        // in case that no entity is found in database, we need to change gameId of provided entity so idGenerator sequence is not broken
        if (dbEntityOptional.isEmpty()) {
            copiedEntity.setGameId(idGenerator.getAndIncrement());
            gameDbList.add(copiedEntity);
            return new GameEntity(copiedEntity);
        }
        GameEntity foundEntity = dbEntityOptional.get();
        foundEntity.setCreationTimestamp(copiedEntity.getCreationTimestamp());
        foundEntity.setAwayTeam(copiedEntity.getAwayTeam());
        foundEntity.setHomeTeam(copiedEntity.getHomeTeam());
        // returns copy, to prevent user from updating stored instance in database from calling code
        return new GameEntity(foundEntity);
    }

    /**
     * Returns optional of first element that matches gameId.
     * @param gameId - element id that is searched by.
     * @return Optional of find element.
     */
    public Optional<GameEntity> findGameById(int gameId) {

        return gameDbList.stream()
                .filter(gameEntity -> gameId == gameEntity.getGameId()).findFirst();
    }

    /**
     * Removes game from database.
     * @param toRemoveEntity contains gameId by which the entity should be removed.
     * @return {@code true} if {@link GameEntity} with corresponding gameId was found in database.
     */
    public boolean removeGame(@NotNull GameEntity toRemoveEntity) {

        Assert.notNull(toRemoveEntity, "toRemoveEntity cannot be null");

        return gameDbList.removeIf(dbEntity -> toRemoveEntity.getGameId().equals(dbEntity.getGameId()));
    }
}
