/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.comaparator;

import java.util.Comparator;

import com.sportradar.sukenik.world.cup.score.board.db.model.GameEntity;

public class TotalScoreDescendingTimestampAscendingGameComparator implements Comparator<GameEntity> {

    @Override
    public int compare(GameEntity o1, GameEntity o2) {

        if (null == o1 && null == o2) {
            return 0;
        }
        if (null == o1) {
            return -1;
        }
        if (null == o2) {
            return 1;
        }
        if (o1.getTotalScore() != o2.getTotalScore()) {
            return o2.getTotalScore() - o1.getTotalScore();
        }
        return (int) (o2.getCreationTimestamp().getTime() - o1.getCreationTimestamp().getTime());
    }
}
