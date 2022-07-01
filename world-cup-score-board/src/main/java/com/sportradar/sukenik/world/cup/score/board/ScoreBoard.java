/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board;

import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DefaultDependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.service.ScoreBoardService;

public class ScoreBoard {

    private final ScoreBoardService scoreBoardService;

    private ScoreBoard() {

        DefaultDependencyFactory dependencyFactory = new DefaultDependencyFactory();
        scoreBoardService = dependencyFactory.getDependency(ScoreBoardService.class);
    }

    public ScoreBoard(DependencyFactory dependencyFactory) {

        scoreBoardService = dependencyFactory.getDependency(ScoreBoardService.class);
    }
}
