/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.comaparator;

import java.util.Comparator;

import com.sportradar.sukenik.world.cup.score.board.data.model.GameEntity;
import com.sportradar.sukenik.world.cup.score.board.ScoreBoard;

/**
 * Comparator that contains comparison logic for {@link ScoreBoard}.
 */
public class TotalScoreDescendingTimestampAscendingGameComparator implements Comparator<GameEntity> {

    /**
     * In case that both passed objects are null returns 0 meaning objects are same. In case that one of objects is null
     * returns that not null object should go before the null. Next parameter to compare is total score, in this case returns
     * subtract of scores.
     *
     * Next parameter is to compare creation timestamps - the list should be ordered that the latest added should be before older
     * records (in case that score is equal).
     *
     * If both records have same creation timestamp method {@link GameEntity#getGameId()} decides which record was added later.
     * @param o1 first object to compare.
     * @param o2 second object to compare.
     * @return comparison of 2 nullable object based on required business logic - first compare total score, then creation timestamp
     * and then gameId.
     */
    @Override
    public int compare(GameEntity o1, GameEntity o2) {

        if (null == o1 && null == o2) {
            return 0;
        }
        if (null == o1) {
            return 1;
        }
        if (null == o2) {
            return -1;
        }
        if (o1.getTotalScore() != o2.getTotalScore()) {
            return o2.getTotalScore() - o1.getTotalScore();
        }

        return o2.getGameId() - o1.getGameId();
    }
}
