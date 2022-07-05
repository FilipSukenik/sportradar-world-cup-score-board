/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.dependency.injection;

import java.util.concurrent.atomic.AtomicInteger;

import com.sportradar.sukenik.world.cup.score.board.data.ScoreBoardDaoImpl;
import com.sportradar.sukenik.world.cup.score.board.mapper.impl.GameEntityDtoMapper;
import com.sportradar.sukenik.world.cup.score.board.mapper.impl.TeamEntityDtoMapper;
import com.sportradar.sukenik.world.cup.score.board.service.ScoreBoardServiceImpl;

public class DefaultDependencyFactory extends DependencyFactory {

    @Override
    protected void initDependencies() {

        // add dao
        addDependency(ScoreBoardDaoImpl.class, new ScoreBoardDaoImpl(new AtomicInteger(1)));

        // add mappers
        addDependency(TeamEntityDtoMapper.class, new TeamEntityDtoMapper());
        addDependency(GameEntityDtoMapper.class, new GameEntityDtoMapper(getDependency(TeamEntityDtoMapper.class)));

        // add service
        addDependency(ScoreBoardServiceImpl.class, new ScoreBoardServiceImpl(
                getDependency(ScoreBoardDaoImpl.class), getDependency(GameEntityDtoMapper.class)
        ));
    }
}
