package com.sportradar.sukenik.world.cup.score.board.dependency.injection;

import java.util.concurrent.atomic.AtomicInteger;

import com.sportradar.sukenik.world.cup.score.board.ScoreBoard;
import com.sportradar.sukenik.world.cup.score.board.data.ScoreBoardDao;
import com.sportradar.sukenik.world.cup.score.board.data.ScoreBoardDaoImpl;
import com.sportradar.sukenik.world.cup.score.board.mapper.impl.GameEntityDtoMapper;
import com.sportradar.sukenik.world.cup.score.board.mapper.impl.TeamEntityDtoMapper;
import com.sportradar.sukenik.world.cup.score.board.service.ScoreBoardServiceImpl;

public class DefaultDependencyFactory extends DependencyFactory {

    public DefaultDependencyFactory() {

    }

    @Override
    protected void initDependencies() {

        // add dao
        addDependency(ScoreBoardDao.class, new ScoreBoardDaoImpl(new AtomicInteger(1)));

        // add mappers
        addDependency(TeamEntityDtoMapper.class, new TeamEntityDtoMapper());
        addDependency(GameEntityDtoMapper.class, new GameEntityDtoMapper(getDependency(TeamEntityDtoMapper.class)));

        // add service
        addDependency(ScoreBoard.class, new ScoreBoardServiceImpl(
                getDependency(ScoreBoardDao.class), getDependency(GameEntityDtoMapper.class)
        ));
    }
}
