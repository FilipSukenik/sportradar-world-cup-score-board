package com.sportradar.sukenik.world.cup.score.board.data;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;

/**
 * Interface that defines all methods that should be implemented in specific Data Access Objects.
 */
public interface ScoreBoardDao {

    /**
     * Returns database list ordered by insert order.
     * @return Copy of all objects that dao stores.
     */
    @NotNull
    List<GameEntity> getGameDbList();

    /**
     * In case that entity is not present in database or has {@link GameEntity#getGameId()} equal to null the entity is saved into database.
     * In case that entity is already in database, it's values are updated.
     * @param toStoreEntity entity that should be saved.
     * @return Deep copy of stored entity.
     */
    @NotNull
    GameEntity save(@NotNull GameEntity toStoreEntity);

    /**
     * Searches database by id and returns {@link Optional} with found object or {@link Optional} with null.
     * @param gameId id of game that client wants to get from database
     * @return {@link Optional} with found or null entity.
     */
    @NotNull
    Optional<GameEntity> findGameById(Integer gameId);

    /**
     * Removes game from database
     * @param toRemoveEntity game that should be removed.
     * @return {@code true} if entity was successfully removed, false if entity was not removed.
     */
    boolean removeGame(@NotNull GameEntity toRemoveEntity);
}
