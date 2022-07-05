package com.sportradar.sukenik.world.cup.score.board.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;

/**
 * Data component that stores in data in memory. Contains {@link Map} of all games on score board, where gamId is key
 * and {@link GameEntity} as value. {@link #gameDbMap} is final field so cannot be changed
 * from outside.
 */
public class ScoreBoardDaoImpl implements ScoreBoardDao {

    private final Map<Integer, GameEntity> gameDbMap;

    private final AtomicInteger idGenerator;

    public ScoreBoardDaoImpl(AtomicInteger idGenerator) {

        this.idGenerator = idGenerator;
        this.gameDbMap = new LinkedHashMap<>();
    }

    /**
     * Returns copy of the list of values, however, all objects are deep copies, so alteration of object from calling code does not
     * change value in database.
     * @return copy of {@link ArrayList} filled with copies of {@link GameEntity}.
     */
    @Override
    @NotNull
    public List<GameEntity> getGameDbList() {

        return gameDbMap.values().stream()
                .map(GameEntity::new)
                .collect(Collectors.toList());
    }

    /**
     * Save game into database. In case that {@code toStoreEntity}'s method {@link GameEntity#getGameId()} returns null
     * the entity receives id from {@link ScoreBoardDaoImpl#idGenerator} and entity is stored in database. In case that
     * {@code toStoreEntity} already has id, first the database is searched whether it already contains entity with same {@link GameEntity#getGameId()}.
     * If no entity is found adds {@code toStoreEntity} into database, else we update values of existing entity.
     * @param toStoreEntity entity that wants to be stored to database.
     */
    @Override
    @NotNull
    public GameEntity save(@NotNull GameEntity toStoreEntity) {

        Assert.notNull(toStoreEntity, "cannot save null into database");

        // make copy so the client storing object cannot update value in database after storing
        GameEntity copiedEntity = new GameEntity(toStoreEntity);

        if (null == copiedEntity.getGameId()) {
            copiedEntity.setGameId(idGenerator.getAndIncrement());
            gameDbMap.put(copiedEntity.getGameId(), copiedEntity);
            return new GameEntity(copiedEntity);
        }
        GameEntity dbEntity = gameDbMap.get(copiedEntity.getGameId());

        // in case that no entity is found in database, we need to change gameId of provided entity so idGenerator sequence is not broken
        if (null == dbEntity) {
            copiedEntity.setGameId(idGenerator.getAndIncrement());
            gameDbMap.put(copiedEntity.getGameId(), copiedEntity);
            return new GameEntity(copiedEntity);
        }
        dbEntity.setAwayTeam(copiedEntity.getAwayTeam());
        dbEntity.setHomeTeam(copiedEntity.getHomeTeam());
        // returns copy, to prevent user from updating stored instance in database from calling code
        return new GameEntity(dbEntity);
    }

    /**
     * Returns optional of first element that matches gameId.
     * @param gameId - element id that is searched by.
     * @return Optional of found or not found element.
     */
    @Override
    @NotNull
    public Optional<GameEntity> findGameById(Integer gameId) {

        return Optional.ofNullable(gameDbMap.get(gameId));
    }

    /**
     * Removes game from database.
     * @param toRemoveEntity contains gameId by which the entity should be removed.
     * @return {@code true} if {@link GameEntity} with corresponding gameId was found in database.
     */
    @Override
    public boolean removeGame(@NotNull GameEntity toRemoveEntity) {

        Assert.notNull(toRemoveEntity, "toRemoveEntity cannot be null");

        GameEntity removedEntity = gameDbMap.remove(toRemoveEntity.getGameId());
        return removedEntity != null;
    }
}
